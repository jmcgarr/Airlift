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

import org.antlr.stringtemplate.StringTemplateGroup;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.JavaFileManager;
import javax.tools.StandardLocation;
import java.io.Writer;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// TODO: Auto-generated Javadoc
/**
 * The Class Generator.
 */
public abstract class Generator
{
    
    /** The comment. */
    protected String comment = "This code has been generated by airlift. Do not modify this code. Instead, you may extend this class with your own concrete implementation located under src/java.";

    /** The processing env. */
    protected ProcessingEnvironment processingEnv;

    /**
     * Gets the persistable set.
     *
     * @return the persistable set
     */
    protected Set getPersistableSet() { return persistableSet; }
    
    /**
     * Gets the numeric set.
     *
     * @return the numeric set
     */
    protected Set getNumericSet() { return numericSet; }
    
    /** The string template group. */
    protected StringTemplateGroup stringTemplateGroup;

	/** The persistable set. */
	protected Set persistableSet;
    
    /** The numeric set. */
    protected Set numericSet;
    
    /**
     * Gets the string template group.
     *
     * @return the string template group
     */
    protected StringTemplateGroup getStringTemplateGroup() { return stringTemplateGroup; }

	/**
	 * Sets the persistable set.
	 *
	 * @param _persistableSet the new persistable set
	 */
	protected void setPersistableSet(Set _persistableSet) { persistableSet = _persistableSet; }
    
    /**
     * Sets the numeric set.
     *
     * @param _numericSet the new numeric set
     */
    protected void setNumericSet(Set _numericSet) { numericSet = _numericSet; }
    
    /**
     * Sets the string template group.
     *
     * @param _stringTemplateGroup the new string template group
     */
    protected void setStringTemplateGroup(StringTemplateGroup _stringTemplateGroup) { stringTemplateGroup = _stringTemplateGroup; }

    /**
     * Instantiates a new generator.
     */
    public Generator()
    {
		setStringTemplateGroup(new StringTemplateGroup("airlift"));
		setPersistableSet(new HashSet());
		setNumericSet(new HashSet());

		getNumericSet().add("long");
		getNumericSet().add("int");
		getNumericSet().add("short");
		getNumericSet().add("double");
		getNumericSet().add("float");
		getNumericSet().add("java.lang.Long");
		getNumericSet().add("java.lang.Integer");
		getNumericSet().add("java.lang.Short");
		getNumericSet().add("java.lang.Double");
		getNumericSet().add("java.lang.Float");
		getNumericSet().add("java.math.BigDecimal");
		getNumericSet().add("java.math.BigInteger");
	}

    /**
     * Gets the nullable constraint.
     *
     * @param _nullable the _nullable
     * @return the nullable constraint
     */
    protected String getNullableConstraint(String _nullable)
    {
		String nullableConstraint = "NOT NULL";

		if ("true".equals(_nullable) == true)
		{
			nullableConstraint = "";
		}

		return nullableConstraint;
    }

    /**
     * Lower the first character.
     *
     * @param _string the _string
     * @return the string
     */
    protected String lowerTheFirstCharacter(String _string)
    {
		return airlift.util.AirliftUtil.lowerTheFirstCharacter(_string);
    }

    /**
     * Upper the first character.
     *
     * @param _string the _string
     * @return the string
     */
    protected String upperTheFirstCharacter(String _string)
    {
		return airlift.util.AirliftUtil.upperTheFirstCharacter(_string);
	}

    /**
     * Gets the setter name.
     *
     * @param _name the _name
     * @return the setter name
     */
    protected String getSetterName(String _name)
    {
		return "set" + upperTheFirstCharacter(_name);
    }

    /**
     * Gets the getter name.
     *
     * @param _name the _name
     * @return the getter name
     */
    protected String getGetterName(String _name)
    {
		return "get" + upperTheFirstCharacter(_name);
    }

    /**
     * Find value.
     *
     * @param _annotation the _annotation
     * @param _attributeName the _attribute name
     * @return the string
     */
    protected String findValue(Annotation _annotation, String _attributeName)
    {
		String value = null;

		if (_annotation != null)
		{
			Object rawValue = _annotation.getParameterValue(_attributeName);
			if (rawValue != null) { value = rawValue.toString(); }
		}

		if (value != null)
		{
			value = value.trim();
			value = value.replaceAll("^\"", "");
			value = value.replaceAll("\"$", "");
		}

		return value;
	}

	/**
	 * Find raw value.
	 *
	 * @param _annotation the _annotation
	 * @param _attributeName the _attribute name
	 * @return the object
	 */
	protected Object findRawValue(Annotation _annotation, String _attributeName)
	{
		Object value = null;

		if (_annotation != null)
		{
			value = _annotation.getParameterValue(_attributeName);
		}
		
		return value;
	}

    /**
     * Extract field.
     *
     * @param _annotation the _annotation
     * @param _defaultName the _default name
     * @return the string
     */
    protected String extractField(Annotation _annotation, String _defaultName)
    {
		String fieldName = null;

		if (_annotation != null)
		{
			Object rawValue = _annotation.getParameterValue("field()");
			if (rawValue != null) { fieldName = rawValue.toString(); }

			if (fieldName != null && "".equals(fieldName) == false && "\"\"".equals(fieldName) == false)
			{
				fieldName = fieldName.trim();
				fieldName = fieldName.replaceAll("^\"","");
				fieldName = fieldName.replaceAll("\"$","");
			}
			else
			{
				fieldName = _defaultName;
			}
		}

		return fieldName;
    }

    /**
     * Extract label.
     *
     * @param _annotation the _annotation
     * @param _defaultName the _default name
     * @return the string
     */
    protected String extractLabel(Annotation _annotation, String _defaultName)
    {
		String labelName = null;

		if (_annotation != null)
		{
			labelName = ((String) _annotation.getParameterValue("label()")).trim();

			if (labelName != null && "".equals(labelName) == false && "\"\"".equals(labelName) == false)
			{
				labelName = labelName.replaceAll("^\"","");
				labelName = labelName.replaceAll("\"$","");
			}
			else
			{
				labelName = _defaultName;
			}
		}

		return labelName;
    }

    /**
     * Prepare parameter value.
     *
     * @param _annotation the _annotation
     * @param _parameterName the _parameter name
     * @return the string
     */
    protected String prepareParameterValue(Annotation _annotation, String _parameterName)
    {
		Object parameterValue = _annotation.getParameterValue(_parameterName);

		if (parameterValue instanceof java.lang.String)
		{
			parameterValue = "\"" + parameterValue + "\"";
		}

		return parameterValue.toString();
    }

    /**
     * Write resource file.
     *
     * @param _fileName the _file name
     * @param _packageName the _package name
     * @param _content the _content
     * @param _element the _element
     */
    protected void writeResourceFile(String _fileName, String _packageName, String _content, Element _element)
    {
		writeResourceFile(_fileName, _packageName, _content, _element, processingEnv);
    }
    
    /**
     * Write resource file.
     *
     * @param _fileName the _file name
     * @param _packageName the _package name
     * @param _content the _content
     * @param _element the _element
     * @param _processingEnv the _processing env
     */
    protected void writeResourceFile(String _fileName, String _packageName, String _content, Element _element, ProcessingEnvironment _processingEnv)
    {
		processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Generating Resource file: " + _fileName);

		Writer writer = null;

		try
		{
			JavaFileManager.Location location = StandardLocation.SOURCE_OUTPUT;
			FileObject fileObject = processingEnv.getFiler().createResource(location, _packageName, _fileName, _element);
			writer = fileObject.openWriter();
			writer.write(_content);
		}
		catch (Throwable t)
		{
			t.printStackTrace();
			processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, t.toString());
		}
		finally
		{
			if (writer != null) { try { writer.close(); } catch (Throwable t) {} };
		}
    }

    /**
     * Write java file.
     *
     * @param _fileName the _file name
     * @param _content the _content
     * @param _element the _element
     */
    protected void writeJavaFile(String _fileName, String _content, Element _element)
    {
		writeJavaFile(_fileName, _content, _element, processingEnv);
    }
    
    /**
     * Write java file.
     *
     * @param _fileName the _file name
     * @param _content the _content
     * @param _element the _element
     * @param _processingEnv the _processing env
     */
    protected void writeJavaFile(String _fileName, String _content, Element _element, ProcessingEnvironment _processingEnv)
    {
		_processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Generating Java file: " + _fileName);

		Writer writer = null;

		try
		{
			FileObject fileObject = _processingEnv.getFiler().createSourceFile(_fileName, _element);
			writer = fileObject.openWriter();
			writer.write(_content);
		}
		catch (Throwable t)
		{
			t.printStackTrace();
			_processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, t.toString());
		}
		finally
		{
			if (writer != null) { try { writer.close(); } catch (Throwable t) {} };
		}
    }

    /**
     * Checks if is persistable.
     *
     * @param _type the _type
     * @return true, if is persistable
     */
    protected boolean isPersistable(String _type)
	{
		//Use to be that only primitives were persistable.  Now all
		//objects are persistable.  Note that not all objects are
		//presentable however.
	    processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Checking this type: " + _type);
		boolean isPersistable = true;

		return isPersistable;
    }

    /**
     * Generate result set getter.
     *
     * @param _javaType the _java type
     * @return the string
     */
    protected String generateResultSetGetter(String _javaType)
    {
		String getterType = "getObject";

		if (_javaType.equals("java.lang.String") == true ||
			  _javaType.equals("airlift.domain.Link") == true ||
			  _javaType.equals("airlift.domain.Link[]") == true)
		{
			getterType = "getString";
		}
		else if (_javaType.equals("long") == true ||
			 _javaType.equals("java.lang.Long") == true
			)
		{
			getterType = "getLong";
		}
		else if (_javaType.equals("java.math.BigDecimal") == true)
		{
			getterType = "getBigDecimal";
		}
		else if (_javaType.equals("int") == true ||
			 _javaType.equals("java.lang.Integer") == true
			)
		{
			getterType = "getInt";
		}
		else if (_javaType.equals("short") == true ||
			 _javaType.equals("java.lang.Short") == true
			)
		{
			getterType = "getShort";
		}
		else if (_javaType.equals("double") == true ||
			 _javaType.equals("java.lang.Double") == true
			)
		{
			getterType = "getDouble";
		}
		else if (_javaType.equals("float") == true ||
			 _javaType.equals("java.lang.Float") == true
			)
		{
			getterType = "getFloat";
		}
		else if (_javaType.equals("java.sql.Date") == true)
		{
			getterType = "getDate";
		}
		else if (_javaType.equals("java.util.Date") == true)
		{
			getterType = "getTimestampAsDate";
		}
		else if (_javaType.equals("java.sql.Time") == true)
		{
			getterType = "getTime";
		}
		else if (_javaType.equals("java.sql.Timestamp") == true)
		{
			getterType = "getTimestamp";
		}
		else if (_javaType.equals("byte") == true ||
			 _javaType.equals("java.lang.Byte") == true
			)
		{
			getterType = "getByte";
		}
		else if (_javaType.equals("Boolean") == true ||
			 _javaType.equals("java.lang.Boolean") == true
			)
		{
			getterType = "getBoolean";
		}
		else
		{
			throw new RuntimeException("Unsupported type: " + _javaType);
		}

		return getterType;
    }


    /**
     * Checks if is numeric type.
     *
     * @param _type the _type
     * @return true, if is numeric type
     */
    public boolean isNumericType(String _type)
    {
		return getNumericSet().contains(_type);
    }

	/**
	 * Determine format util string.
	 *
	 * @param _getterName the _getter name
	 * @param _type the _type
	 * @param _format the _format
	 * @return the string
	 */
	protected String determineFormatUtilString(String _getterName, String _type, String _format)
	{
		String formatUtilString = "airlift.util.FormatUtil.format(" + _getterName + "())";

		if (_type.equalsIgnoreCase("java.sql.Date") == true)
		{
			String[] tokens = _format.split(",");
			String format =  (tokens.length > 0 && tokens[0] != null && tokens[0].equalsIgnoreCase(".*") == false) ? tokens[0] : "yyyy-MM-dd";
			formatUtilString = "airlift.util.FormatUtil.format(" + _getterName + "(), \"" + format + "\")";
		}
		else if (_type.equalsIgnoreCase("java.util.Date") == true)
		{
			String[] tokens = _format.split(",");
			String format =  (tokens.length > 0 && tokens[0] != null && tokens[0].equalsIgnoreCase(".*") == false) ? tokens[0] : "yyyy-MM-dd HH:mm:ss";
			formatUtilString = "airlift.util.FormatUtil.format(" +  _getterName + "(), \"" + format + "\")";
		}
		else if (_type.equalsIgnoreCase("java.sql.Timestamp") == true)
		{
			String[] tokens = _format.split(",");
			String format =  (tokens.length > 0 && tokens[0] != null && tokens[0].equalsIgnoreCase(".*") == false) ? tokens[0] : "yyyy-MM-dd HH:mm:ss";

			formatUtilString = "airlift.util.FormatUtil.format(" +  _getterName + "(), \"" + format + "\")";
		}

		return formatUtilString;
	}

	/**
	 * Checks if is array type.
	 *
	 * @param _type the _type
	 * @return true, if is array type
	 */
	public boolean isArrayType(String _type)
	{
		return (_type != null) ? _type.contains("[]") : false;
	}

	/**
	 * Prints the message.
	 *
	 * @param _message the _message
	 */
	public void printMessage(String _message)
	{
		processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, _message);
	}

	/**
	 * Determine encryption conversion function.
	 *
	 * @param _type the _type
	 * @return the string
	 */
	public String determineEncryptionConversionFunction(String _type)
	{
		return "convert";
	}
	
	/**
	 * Determine decryption conversion function.
	 *
	 * @param _type the _type
	 * @return the string
	 */
	public String determineDecryptionConversionFunction(String _type)
	{
		String decryptionConversionFunction = "convertToString";
		
		if ("java.lang.String".equalsIgnoreCase(_type) == true)
		{
			decryptionConversionFunction = "convertToString";
		}
		else if ("java.util.Date".equalsIgnoreCase(_type) == true)
		{
			decryptionConversionFunction = "convertToDate";
		}
		else if ("java.lang.Long".equalsIgnoreCase(_type) == true)
		{
			decryptionConversionFunction = "convertToLong";
		}
		else if ("java.lang.Integer".equalsIgnoreCase(_type) == true)
		{
			decryptionConversionFunction = "convertToInteger";
		}
		else if ("java.lang.Double".equalsIgnoreCase(_type) == true)
		{
			decryptionConversionFunction = "convertToDouble";
		}
		else if ("java.lang.Float".equalsIgnoreCase(_type) == true)
		{
			decryptionConversionFunction = "convertToFloat";
		}
		else if ("java.lang.Short".equalsIgnoreCase(_type) == true)
		{
			decryptionConversionFunction = "convertToShort";
		}
		else if ("java.lang.Byte[]".equalsIgnoreCase(_type) == true)
		{
			decryptionConversionFunction = "convertToByteArray";
		}

		return decryptionConversionFunction;
	}

    /**
     * Generate.
     *
     * @param _appName the _app name
     * @param _directory the _directory
     * @param _element the _element
     * @param _domainObjectModel the _domain object model
     * @param _elementNameToDomainObjectModelMap the _element name to domain object model map
     */
    protected abstract void generate(String _appName,
					String _directory,
					Element _element,
					DomainObjectModel _domainObjectModel,
					Map<String, DomainObjectModel> _elementNameToDomainObjectModelMap);
}