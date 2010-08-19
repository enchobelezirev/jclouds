/**
 *
 * Copyright (C) 2010 Cloud Conscious, LLC. <info@cloudconscious.com>
 *
 * ====================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ====================================================================
 */

package org.jclouds.gogrid.compute.suppliers;

import java.util.Set;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.jclouds.compute.domain.Image;
import org.jclouds.compute.domain.OperatingSystem;
import org.jclouds.compute.domain.OsFamily;
import org.jclouds.compute.domain.internal.ImageImpl;
import org.jclouds.compute.reference.ComputeServiceConstants;
import org.jclouds.compute.strategy.PopulateDefaultLoginCredentialsForImageStrategy;
import org.jclouds.domain.Credentials;
import org.jclouds.gogrid.GoGridClient;
import org.jclouds.gogrid.domain.ServerImage;
import org.jclouds.gogrid.util.GoGridUtils;
import org.jclouds.logging.Logger;

import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;

/**
 * 
 * @author Adrian Cole
 */
@Singleton
public class GoGridImageSupplier implements Supplier<Set<? extends Image>> {
   public static final Pattern GOGRID_OS_NAME_PATTERN = Pattern.compile("([a-zA-Z]*)(.*)");

   @Resource
   @Named(ComputeServiceConstants.COMPUTE_LOGGER)
   protected Logger logger = Logger.NULL;

   private final GoGridClient sync;
   private final PopulateDefaultLoginCredentialsForImageStrategy authenticator;

   @Inject
   GoGridImageSupplier(final GoGridClient sync, PopulateDefaultLoginCredentialsForImageStrategy authenticator) {
      this.sync = sync;
      this.authenticator = authenticator;
   }

   @Override
   public Set<? extends Image> get() {
      final Set<Image> images = Sets.newHashSet();
      logger.debug(">> providing images");
      Set<ServerImage> allImages = sync.getImageServices().getImageList();
      for (ServerImage from : allImages) {
         OsFamily osFamily = null;
         String osName = from.getOs().getName();
         String osArch = from.getArchitecture().getDescription();
         String osVersion = null;// TODO
         String osDescription = from.getOs().getDescription();
         boolean is64Bit = (from.getOs().getName().indexOf("64") != -1 || from.getDescription().indexOf("64") != -1);

         String description = from.getDescription();
         String version = "";

         String matchedOs = GoGridUtils.parseStringByPatternAndGetNthMatchGroup(osName, GOGRID_OS_NAME_PATTERN, 1);
         try {
            osFamily = OsFamily.fromValue(matchedOs.toLowerCase());
         } catch (IllegalArgumentException e) {
            logger.debug("<< didn't match os(%s)", matchedOs);
         }
         Credentials defaultCredentials = authenticator.execute(from);
         // TODO determine DC images are in
         OperatingSystem os = new OperatingSystem(osFamily, osName, osVersion, osArch, osDescription, is64Bit);

         images.add(new ImageImpl(from.getId() + "", from.getFriendlyName(), from.getId() + "", null, null,
                  ImmutableMap.<String, String> of(), os, description, version, defaultCredentials));
      }
      logger.debug("<< images(%d)", images.size());
      return images;
   }
}