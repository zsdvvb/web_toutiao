(function (window, undefined) {
    var PopupLogin = Base.getClass('main.component.PopupLogin');
    Base.ready({
        initialize: fInitialize,
        binds: {
            //.表示class #表示id
            'click .js-login': fClickLogin
        }
    });

    function fInitialize() {
        if (window.loginpop > 0) {
            fClickLogin();
        }
    }
    
    function fClickLogin() {
        var that = this;
        PopupLogin.show({
            listeners: {
                login: function () {
                    //alert('login');
                    window.location.reload();
                },
                register: function () {
                    // window.location.reload();
                    if (window.location.href.indexOf("?") > 0) {
                        window.location.href = window.location.href.substring(0, window.location.href.indexOf("?"));
                    } else {
                        window.location.href = window.location.href;
                    }
                }
            }
        });
    }

})(window);