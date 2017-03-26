String.prototype.trim = function () {
    return this.replace(/(^\s*)|(\s*$)/g, "");
};

String.prototype.ltrim = function () {
    return this.replace(/(^\s*)/g, "");
};

String.prototype.rtrim = function () {
    return this.replace(/(\s*$)/g, "");
};

function isTnteger(str) {
    var regu = /^[-]{0,1}[0-9]{1,}$/;
    return regu.test(str);
};

function urlReplaceString(url) {
    url = url.replace(/%/g,"%25");
    url = url.replace(/\+/g,"%2B");
    // url = url.replace(/ /g,"%20");
    // url = url.replace("/","%2F");
    // url = url.replace(/?/g,"%3F");
    url = url.replace(/#/g,"%23");
    url = url.replace(/&/g,"%26");
    // url = url.replace(/=/g,"%3D");
    return url;
};

