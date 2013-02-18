var web = require('./web');

exports.typeOf = function typeOf(value)
{
	var s = typeof value;

	if (s === 'object')
	{
		if (value)
		{
			if (typeof value.length === 'number' &&
				  !(value.propertyIsEnumerable('length')) &&
				  typeof value.splice === 'function')
			{
				s = 'array';
			}
		}
		else
		{
			s = 'null';
		}
	}

	return s;
};

exports.isEmpty = function isEmpty(o)
{
	var i, v, isEmpty = true;

	if ( this.typeOf(o) === 'object')
	{
		for (i in o)
		{
			v = o[i];
			
			if (v !== undefined && this.typeOf(v) !== 'function')
			{
				isEmpty = false;
			}
		}
	}
	else
	{
		isEmpty = false;
	}

	return isEmpty;
};

exports.createClass = function createClass(_className)
{
	return Packages.java.lang.Class.forName(_className);
};

exports.isWhitespace = function isWhitespace(_string)
{
	return Packages.org.apache.commons.lang.StringUtils.isWhitespace(_string);
};

exports.hasValue = function hasValue(_value)
{
	return (_value !== null && _value !== undefined);
};

var ErrorReporter = function()
{
	var errors = {};

	this.allErrors = function() { return errors; };
	this.getErrors = function(_name)
	{
		if (_name === undefined || _name === null)
		{
			throw new Error('getErrors expects an error name.  To get allErrors use allErrors instead');
		}
		
		return errors[_name];
	}

	this.report = function(_name, _error)
	{
		if (_name === null || _name === undefined)
		{
			throw new Error('Cannot report and error with an undefined or null name');
		}
		
		var errorList = errors[_name]||[];

		if (_error && Array.isArray(_error) === true)
		{
			errorList = errorList.concat(_error);
		}
		else if (_error && _error.name !== null && _error.name !== undefined)
		{
			errorList.push(_error);
		}
		else if (_error && typeof _error === 'string')
		{
			var error = {name: _name, message: _error, category: arguments[2]||''};
			errorList.push(error);
		}

		errors[_name] = errorList;
	};
};

exports.createErrorReporter = function()
{
	return new ErrorReporter();
}

exports.multiTry = function multiTry(_executable, _tryCount, _message, _completeFailure)
{
	var result, success = false;

	for (var i = 0; i < _tryCount && success === false; i++)
	{
		try
		{
			result = _executable(i);
			success = true;
		}
		catch(e)
		{
			var log = web.getLog();
			log.warning(_message + " " + e.toString());

			if (i >= _tryCount)
			{
				_completeFailure && _completeFailure(_tries);
				log.severe("After this many tries: " + _tryCount + " - " +  e.toString());
				throw e;
			}
		}
	}

	return result;
};

exports.createDate = function createDate(_milliseconds)
{
	var date;
	
	if (this.hasValue(_milliseconds) === true)
	{
		date = new Packages.java.util.Date(_milliseconds);
	}
	else
	{
		date = new Packages.java.util.Date();
	}
	
	return date; 
};

exports.createCalendar = function createCalendar(_config)
{
	var date = (_config && _config.date) ? _config.date : null;
	var dateOffset = (_config && _config.dateOffset) ? _config.dateOffset : 0;
	var dateOffsetType = (_config && _config.dateOffsetType) ? _config.dateOffsetType : Packages.java.util.Calendar.MILLISECOND;
	var timeZone = (_config && _config.timeZone) ? _config.timeZone : web.getTimezone();
	var locale = (_config && _config.locale) ? _config.locale : web.getLocale();

	if (this.hasValue(date) === true)
	{
		var calendar = Packages.java.util.Calendar.getInstance(timeZone, locale);
		calendar.setTime(date);
		calendar.setTimeZone(timeZone);
	}
	else
	{
		var calendar = Packages.java.util.Calendar.getInstance(timeZone, locale);
	}

	calendar.add(dateOffsetType, dateOffset);

	return calendar;
};

exports.guid = function guid(_length)
{
	if (_length) { var id = Packages.airlift.util.IdGenerator.generate(_length); }
	else { var id = Packages.airlift.util.IdGenerator.generate(); }

	return id; 
};

exports.trim = function trim(_string)
{
	var trimmed = _string;

	if (_string)
	{
		trimmed = Packages.org.apache.commons.lang.StringUtils.trim(_string);

		if (this.typeOf(_string) === 'string')
		{
			trimmed = trimmed + '';
		}
	}

	return trimmed;
};

exports.print = function print()
{
	var args = Array.prototype.slice.call(arguments, 0);

	for (var i = 0, length = args.length; i < length; i++)
	{
		if (i === 0)
		{
			Packages.java.lang.System.out.print(args[i]||'');
		}
		else
		{
			Packages.java.lang.System.out.print(' ' + args[i]||'');
		}
	}
};

exports.println = function println()
{
	var args = Array.prototype.slice.call(arguments, 0);

	exports.print.apply(this, args);
	
	Packages.java.lang.System.out.println('');
};

exports.value = function value(_candidate, _default)
{
	var candidate;

	if (this.hasValue(_candidate) === true)
	{
		candidate = _candidate;
	}
	else
	{
		candidate = _default;
	}

	return candidate;
};