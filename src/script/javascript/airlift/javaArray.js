/**
 * @author <a href="mailto:bediako.george@lucidtechnics.com">Bediako
 * George</a>
 *
 * @description - Creates a Java array of the specified size and type.
 * If an initialization array is provided tht contents of that array
 * are copied to the Java array.
 *
 * @param _size - the initial size of the array.
 * @param _type - the Java type of the array
 * @param _initializer - an array of values that should be copied to
 * the array.
 *
 * @returns a Java type array
 *
 * @example
 * var stringArray = airlift.createArray(3, "java.lang.String",
 * ["Bediako", "Loki"]); //should return java.lang.String[] of size 3
 * with the first two slots containing "Bediako" and "Loki".
 */
exports.createArray = function(_size, _type, _initializer)
{
	var size = _size||0;
	var type = _type||Packages.java.lang.String;
	initializer = _initializer||[];

	var newArray = java.lang.reflect.Array.newInstance(type, size);
	initializer.forEach(function(_item, _index) { newArray[_index] = _item; });

	return newArray;
};

exports.stringArray = function(_size, _initializer)
{
	return this.createArray(_size, Packages.java.lang.String, _initializer);
};

exports.byteArray = function(_size, _initializer)
{
	return this.createArray(_size, Packages.java.lang.Byte.TYPE, _initializer);
};

exports.byteObjectArray = function(_size, _initializer)
{
	return this.createArray(_size, Packages.java.lang.Byte, _initializer);
};

exports.shortArray = function(_size, _initializer)
{
	return this.createArray(_size, Packages.java.lang.Short.TYPE, _initializer);
};

exports.shortObjectArray = function(_size, _initializer)
{
	return this.createArray(_size, Packages.java.lang.Short, _initializer);
};

exports.charArray = function(_size, _initializer)
{
	return this.createArray(_size, Packages.java.lang.Character.TYPE, _initializer);
};

exports.characterObjectArray = function(_size, _initializer)
{
	return this.createArray(_size, Packages.java.lang.Character, _initializer);
};

exports.intArray = function(_size, _initializer)
{
	return this.createArray(_size, Packages.java.lang.Integer.TYPE, _initializer);
};

exports.integerObjectArray = function(_size, _initializer)
{
	return this.createArray(_size, Packages.java.lang.Integer, _initializer);
};

exports.longArray = function(_size, _initializer)
{
	return this.createArray(_size, Packages.java.lang.Long.TYPE, _initializer);
};

exports.longObjectArray = function(_size, _initializer)
{
	return this.createArray(_size, Packages.java.lang.Long, _initializer);
};

exports.booleanArray = function(_size, _initializer)
{
	return this.createArray(_size, Packages.java.lang.Boolean.TYPE, _initializer);
};

exports.booleanObjectArray = function(_size, _initializer)
{
	return this.createArray(_size, Packages.java.lang.Boolean, _initializer);
};

exports.floatArray = function(_size, _initializer)
{
	return this.createArray(_size, Packages.java.lang.Float.TYPE, _initializer);
};

exports.floatObjectArray = function(_size, _initializer)
{
	return this.createArray(_size, Packages.java.lang.Float, _initializer);
};

exports.doubleArray = function(_size, _initializer)
{
	return this.createArray(_size, Packages.java.lang.Double.TYPE, _initializer);
};

exports.doubleObjectArray = function(_size, _initializer)
{
	return this.createArray(_size, Packages.java.lang.Double, _initializer);
};