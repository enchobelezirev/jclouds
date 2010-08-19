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

package org.jclouds.compute.domain.os;

import org.jclouds.compute.domain.OperatingSystem;
import org.jclouds.compute.domain.OsFamily;

import com.google.common.annotations.Beta;

/**
 * Operating system based on DMTF CIM model.
 * 
 * @author Adrian Cole
 * @see <a href="http://dmtf.org/standards/cim/cim_schema_v2260">DMTF CIM
 *      model</a>
 */
@Beta
public class CIMOperatingSystem extends OperatingSystem {

   public enum OSType {
      /**
       * Other
       */
      OTHER(0, "Other", OsFamily.UNKNOWN, false),
      /**
       * MACOS
       */
      MACOS(1, "MACOS", OsFamily.DARWIN, false),
      /**
       * Solaris
       */
      SOLARIS(28, "Solaris", OsFamily.SOLARIS, false),
      /**
       * LINUX
       */
      LINUX(35, "LINUX", OsFamily.LINUX, false),
      /**
       * FreeBSD
       */
      FREEBSD(41, "FreeBSD", OsFamily.FREEBSD, false),
      /**
       * NetBSD
       */
      NETBSD(42, "NetBSD", OsFamily.NETBSD, false),
      /**
       * OpenBSD
       */
      OPENBSD(64, "OpenBSD", OsFamily.OPENBSD, false),
      /**
       * Not Applicable
       */
      NOT_APPLICABLE(65, "Not Applicable", OsFamily.UNKNOWN, false),
      /**
       * Microsoft Windows Server 2003
       */
      WINDOWS_SERVER_2003(68, "Microsoft Windows Server 2003", OsFamily.WINDOWS, false),
      /**
       * Microsoft Windows Server 2003 64-Bit
       */
      WINDOWS_SERVER_2003_64(69, "Microsoft Windows Server 2003 64-Bit", OsFamily.WINDOWS, true),
      /**
       * Microsoft Windows Server 2008
       */
      WINDOWS_SERVER_2008(75, "Microsoft Windows Server 2008", OsFamily.WINDOWS, false),
      /**
       * Microsoft Windows Server 2008 64-Bit
       */
      WINDOWS_SERVER_2008_64(76, "Microsoft Windows Server 2008 64-Bit", OsFamily.WINDOWS, true),
      /**
       * FreeBSD 64-Bit
       */
      FREEBSD_64(77, "FreeBSD 64-Bit", OsFamily.FREEBSD, true),
      /**
       * RedHat Enterprise Linux
       */
      RHEL(78, "RedHat Enterprise Linux", OsFamily.RHEL, false),
      /**
       * RedHat Enterprise Linux 64-Bit
       */
      RHEL_64(79, "RedHat Enterprise Linux 64-Bit", OsFamily.RHEL, true),
      /**
       * Solaris 64-Bit
       */
      SOLARIS_64(80, "Solaris 64-Bit", OsFamily.SOLARIS, true),
      /**
       * SUSE
       */
      SUSE(81, "SUSE", OsFamily.SUSE, false),
      /**
       * SUSE 64-Bit
       */
      SUSE_64(82, "SUSE 64-Bit", OsFamily.SUSE, true),
      /**
       * SLES
       */
      SLES(83, "SLES", OsFamily.SUSE, false),
      /**
       * SLES 64-Bit
       */
      SLES_64(84, "SLES 64-Bit", OsFamily.SUSE, true),
      /**
       * Novell OES
       */
      NOVELL_OES(85, "Novell OES", OsFamily.SUSE, true),
      /**
       * Mandriva
       */
      MANDRIVA(88, "Mandriva", OsFamily.MANDRIVA, false),
      /**
       * Mandriva 64-Bit
       */
      MANDRIVA_64(89, "Mandriva 64-Bit", OsFamily.MANDRIVA, true),
      /**
       * TurboLinux
       */
      TURBOLINUX(90, "TurboLinux", OsFamily.TURBOLINUX, false),
      /**
       * TurboLinux 64-Bit
       */
      TURBOLINUX_64(91, "TurboLinux 64-Bit", OsFamily.TURBOLINUX, true),
      /**
       * Ubuntu
       */
      UBUNTU(92, "Ubuntu", OsFamily.UBUNTU, false),
      /**
       * Ubuntu 64-Bit
       */
      UBUNTU_64(93, "Ubuntu 64-Bit", OsFamily.UBUNTU, true),
      /**
       * Debian
       */
      DEBIAN(94, "Debian", OsFamily.DEBIAN, false),
      /**
       * Debian 64-Bit
       */
      DEBIAN_64(95, "Debian 64-Bit", OsFamily.DEBIAN, false),
      /**
       * Linux 2.4.x
       */
      LINUX_2_4(96, "Linux 2.4.x", OsFamily.LINUX, false),
      /**
       * Linux 2.4.x 64-Bi
       */
      LINUX_2_4_64(97, "Linux 2.4.x 64-Bit", OsFamily.LINUX, true),
      /**
       * Linux 2.6.x
       */
      LINUX_2_6(98, "Linux 2.6.x", OsFamily.LINUX, false),
      /**
       * Linux 2.6.x 64-Bit
       */
      LINUX_2_6_64(99, "Linux 2.6.x 64-Bit", OsFamily.LINUX, true),
      /**
       * Linux 64-Bit
       */
      LINUX_64(100, "Linux 64-Bit", OsFamily.LINUX, true),
      /**
       * Other 64-Bit
       */
      OTHER_64(101, "Other 64-Bit", OsFamily.UNKNOWN, true),
      /**
       * Microsoft Windows Server 2008 R2
       */
      WINDOWS_SERVER_2008_R2(102, "Microsoft Windows Server 2008 R2", OsFamily.WINDOWS, true),
      /**
       * VMware ESXi
       */
      ESXI(103, "VMware ESXi", OsFamily.ESX, true),
      /**
       * Microsoft Windows 7
       */
      WINDOWS_7(104, "Microsoft Windows 7", OsFamily.WINDOWS, false),
      /**
       * CentOS 32-bit
       */
      CENTOS(105, "CentOS 32-bit", OsFamily.CENTOS, false),
      /**
       * CentOS 64-bit
       */
      CENTOS_64(106, "CentOS 64-bit", OsFamily.CENTOS, true),
      /**
       * Oracle Enterprise Linux 32-bit
       */
      ORACLE_ENTERPRISE_LINUX(107, "Oracle Enterprise Linux 32-bit", OsFamily.OEL, false),
      /**
       * Oracle Enterprise Linux 64-bit
       */
      ORACLE_ENTERPRISE_LINUX_64(108, "Oracle Enterprise Linux 64-bit", OsFamily.OEL, true);

      private final int code;

      public int getCode() {
         return code;
      }

      public String getValue() {
         return value;
      }

      public OsFamily getFamily() {
         return family;
      }

      public boolean is64Bit() {
         return is64Bit;
      }

      private final String value;
      private final OsFamily family;
      private final boolean is64Bit;

      OSType(int code, String value, OsFamily family, boolean is64Bit) {
         this.code = code;
         this.value = value;
         this.family = family;
         this.is64Bit = is64Bit;
      }

      public static OSType fromValue(int code) {
         for (OSType type : values()) {
            if (type.code == code)
               return type;
         }
         return OTHER;
      }

   }

   private OSType osType;

   protected CIMOperatingSystem() {
      super();
   }

   public CIMOperatingSystem(OSType osType, String version, String arch, String description) {
      super(osType.getFamily(), osType.getValue(), version, arch, description, osType.is64Bit());
      this.osType = osType;
   }
   /**
    * CIM OSType of the image
    */
   public OSType getOsType() {
      return osType;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = super.hashCode();
      result = prime * result + ((osType == null) ? 0 : osType.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (!super.equals(obj))
         return false;
      if (getClass() != obj.getClass())
         return false;
      CIMOperatingSystem other = (CIMOperatingSystem) obj;
      if (osType == null) {
         if (other.osType != null)
            return false;
      } else if (!osType.equals(other.osType))
         return false;
      return true;
   }

   @Override
   public String toString() {
      return "[osType=" + osType + ", version=" + getVersion() + ", arch=" + getArch() + ", description="
            + getDescription() + "]";
   }
}