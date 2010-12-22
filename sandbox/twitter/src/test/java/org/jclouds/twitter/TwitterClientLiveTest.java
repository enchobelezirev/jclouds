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

package org.jclouds.twitter;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.testng.Assert.assertTrue;

import java.util.Properties;
import java.util.SortedSet;

import org.jclouds.Constants;
import org.jclouds.logging.log4j.config.Log4JLoggingModule;
import org.jclouds.rest.RestContext;
import org.jclouds.rest.RestContextFactory;
import org.jclouds.twitter.domain.Status;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableSet;
import com.google.inject.Module;

/**
 * Tests behavior of {@code TwitterClient}
 * 
 * @author Adrian Cole
 */
@Test(groups = "live", sequential = true)
public class TwitterClientLiveTest {

   protected TwitterClient client;
   protected RestContext<TwitterClient, TwitterAsyncClient> context;

   protected String provider = "twitter";
   protected String identity;
   protected String credential;
   protected String endpoint;
   protected String apiversion;

   protected void setupCredentials() {
      identity = checkNotNull(System.getProperty("test." + provider + ".identity"), "test." + provider + ".identity");
      credential = System.getProperty("test." + provider + ".credential");
      endpoint = System.getProperty("test." + provider + ".endpoint");
      apiversion = System.getProperty("test." + provider + ".apiversion");
   }

   protected Properties setupProperties() {
      Properties overrides = new Properties();
      overrides.setProperty(Constants.PROPERTY_TRUST_ALL_CERTS, "true");
      overrides.setProperty(Constants.PROPERTY_RELAX_HOSTNAME, "true");
      overrides.setProperty(provider + ".identity", identity);
      if (credential != null)
         overrides.setProperty(provider + ".credential", credential);
      if (endpoint != null)
         overrides.setProperty(provider + ".endpoint", endpoint);
      if (apiversion != null)
         overrides.setProperty(provider + ".apiversion", apiversion);
      return overrides;
   }

   @BeforeGroups(groups = "live")
   public void setupClient() {
      setupCredentials();
      Properties overrides = setupProperties();
      
      //TODO take this out, when the service is registered in jclouds-core/rest.properties
      Properties restProperties = new Properties();
      restProperties.setProperty("twitter.contextbuilder", "org.jclouds.twitter.TwitterContextBuilder");
      restProperties.setProperty("twitter.propertiesbuilder", "org.jclouds.twitter.TwitterPropertiesBuilder");

      context = new RestContextFactory(restProperties).createContext(provider, ImmutableSet.<Module> of(new Log4JLoggingModule()),
            overrides);

      client = context.getApi();
   }

   @AfterGroups(groups = "live")
   void tearDown() {
      if (context != null)
         context.close();
   }

   public void testGetMyMentions() throws Exception {
      SortedSet<Status> response = client.getMyMentions();
      assert null != response;
      long mentions = response.size();
      assertTrue(mentions >= 0);
   }

}
