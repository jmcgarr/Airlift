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

package airlift.servlet.rest; 

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class AirliftUser
{
	public AirliftUser() {}

	@Persistent @PrimaryKey private String id;
	@Persistent private String fullName;
	@Persistent private String shortName;
	@Persistent private String googleUserId;
	@Persistent private String email;
	@Persistent private java.util.List<String> roleList = new java.util.ArrayList<String>();
	@Persistent private boolean active;
	@Persistent private java.util.Date auditPostDate;
	@Persistent private java.util.Date auditPutDate;
	@Persistent private java.util.Date timeOutDate;
	
	public String getId() { return id; }
	public String getFullName() { return fullName; }
	public String getShortName() { return shortName; }
	public String getGoogleUserId() { return googleUserId; }
	public String getEmail() { return email; }
	public java.util.List<String> getRoleList() { return roleList; }
	public boolean getActive() { return active; }
	public java.util.Date getAuditPostDate() { return auditPostDate; }
	public java.util.Date getAuditPutDate() { return auditPutDate; }
	public java.util.Date getTimeOutDate() { return timeOutDate; }
	
	public void setId(String _id) { id = _id; }
	public void setGoogleUserId(String _googleUserId) { googleUserId = _googleUserId; }
	public void setFullName(String _fullName) { fullName = _fullName; }
	public void setShortName(String _shortName) { shortName = _shortName; }
	public void setEmail(String _email) { email = _email; }
	public void setRoleList(java.util.List<String> _roleList) { roleList = _roleList; }
	public void setActive(boolean _active) { active = _active; }
	public void setAuditPostDate(java.util.Date _auditPostDate) { auditPostDate = _auditPostDate; }
	public void setAuditPutDate(java.util.Date _auditPutDate) { auditPutDate = _auditPutDate; }
	public void setTimeOutDate(java.util.Date _timeOutDate) { timeOutDate = _timeOutDate; }
}