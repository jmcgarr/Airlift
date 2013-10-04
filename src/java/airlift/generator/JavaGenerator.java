								   /*
 Copyright 2011, Lucid Technics, LLC.

 Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 except in compliance with the License. You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in
 writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 specific language governing permissions and limitations under the License.
*/

package airlift.generator;

import org.antlr.stringtemplate.StringTemplate;

import javax.lang.model.element.Element;
import java.util.Iterator;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class JavaGenerator.
 */
public class JavaGenerator
   extends Generator
{
	
	/** The comment. */
	public String comment = "This code has been generated by airlift. Do not modify this code. Instead, you may extend this class with your own concrete implementation located under src/java.";

	/* (non-Javadoc)
	 * @see airlift.generator.Generator#generate(java.lang.String, java.lang.String, javax.lang.model.element.Element, airlift.generator.ResourceModel, java.util.Map)
	 */
	public void generate(String _appName,
				String _directory,
				Element _element,
				ResourceModel _resourceModel,
				Map<String, ResourceModel> _elementNameToResourceModelMap)
	{
		String generatedString = generateApplicationProfile(_elementNameToResourceModelMap);
		String fileName =  _directory + "." + _resourceModel.getRootPackageName() + ".airlift.meta." + _resourceModel.getClassName();
		writeJavaFile(fileName, generatedString, _element);
	}
	
	/**
	 * Generate application profile.
	 *
	 * @param _elementNameToResourceModelMap the _element name to domain object model map
	 * @return the string
	 */
	public String generateApplicationProfile(Map<String, ResourceModel> _elementNameToResourceModelMap)
	{
		StringTemplate template = getStringTemplateGroup().getInstanceOf("airlift/language/java/AppProfile");
		boolean isHighLevelAttributesSet = false;
		
		for (ResourceModel resourceModel: _elementNameToResourceModelMap.values())
		{
			if (resourceModel.isAbstractResource() == false)
			{
				if (isHighLevelAttributesSet == false)
				{
					template.setAttribute("package", resourceModel.getRootPackageName());
					template.setAttribute("appName", resourceModel.getAppName());

					isHighLevelAttributesSet = true;
				}

				String resourceName = resourceModel.getClassName().toLowerCase();
				template.setAttribute("resourceName", resourceName);

				if (resourceModel.getIsView() == true)
				{
					template.setAttribute("viewName", resourceName);
					template.setAttribute("viewResourceName", resourceModel.getLookingAt());
				}

				Annotation securable = resourceModel.getResourceAnnotationMap().get("securable");
				//[script] parameterMap --> {collectRoles="all",
				//putRoles="all", postRoles="all", headRoles="all", getRoles="all"}

				for (String method: securable.getParameterMap().keySet())
				{
					String roles = (String) securable.getParameterMap().get(method);
					String[] tokens = roles.split(",");
					String methodName = method.replaceAll("Roles", "").toUpperCase();

					template.setAttribute("methodResourceName", resourceName);
					template.setAttribute("methodName", methodName);

					for (int i = 0; i < tokens.length; i++)
					{
						template.setAttribute("roleResourceName", resourceName);
						template.setAttribute("roleMethodName", methodName);
						template.setAttribute("roleName", tokens[i].replace("\"", ""));
					}
				}
			}
		}

		return template.toString();
	}
}