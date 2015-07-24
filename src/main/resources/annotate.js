/**
 * Stolen from AngularJS
 * http://stackoverflow.com/questions/1007981/how-to-get-function-parameter-names-values-dynamically-from-javascript
 */

(function() {
    var FN_ARGS = /^function\s*[^\(]*\(\s*([^\)]*)\)/m;
    var FN_ARG_SPLIT = /,/;
    var FN_ARG = /^\s*(_?)(.+?)\1\s*$/;
    var STRIP_COMMENTS = /((\/\/.*$)|(\/\*[\s\S]*?\*\/))/mg;
    window.annotate = function(fn) {
        var $inject,
            fnText,
            argDecl,
            last;

        if (typeof fn == 'function') {
            if (!($inject = fn.$inject)) {
                $inject = [];
                fnText = fn.toString().replace(STRIP_COMMENTS, '');
                argDecl = fnText.match(FN_ARGS);
                argDecl[1].split(FN_ARG_SPLIT).forEach(function(arg){
                    arg.replace(FN_ARG, function(all, underscore, name){
                        $inject.push(name);
                    });
                });
                fn.$inject = $inject;
            }
        } else if (isArray(fn)) {
            last = fn.length - 1;
            assertArgFn(fn[last], 'fn')
            $inject = fn.slice(0, last);
        } else {
            assertArgFn(fn, 'fn', true);
        }
        return $inject;
    }
}).call(this);
