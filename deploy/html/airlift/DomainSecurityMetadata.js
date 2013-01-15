var DomainSecurityMetadata = function(_name)
{
	this.name = _name;

	this.collectRoles = {'all': 1};
	this.putRoles = {'all': 1};
	this.postRoles = {'all': 1};
	this.headRoles = {'all': 1};
	this.getRoles = {'all': 1};
	this.deleteRoles = {'noone': 1}
};

exports.create = function(_name)
{
	return new DomainSecurityMetadata(_name);
};
