/*
 Copyright 2007, Lucid Technics, LLC.

 Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 except in compliance with the License. You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in
 writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 specific language governing permissions and limitations under the License.
*/

package airlift; 

public interface AppProfile
{
	public String getAppName();
	public String getRootPackageName();
	public String getDomainShortClassName(String _domainName);
	public String getFullyQualifiedClassName(String _domainName);
	public java.util.Collection getValidDomains();
	public boolean isValidDomain(String _domainName);
	public String getAttributeType(String _domainName, String _attributeName);
	
	public airlift.SecurityContext getSecurityContext();
	public airlift.CachingContext getCachingContext();
}