<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>头条资讯 - 牛客网</title>
    <meta name="viewport" content="width=device-width, minimum-scale=1.0, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta name="keywords" content="读《Web 全栈工程师的自我修养》">
    <meta name="description" content="阅读影浅分享的读《Web 全栈工程师的自我修养》，就在牛客网。">

    <link rel="stylesheet" type="text/css" href="/styles/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/styles/font-awesome.min.css">

    <link rel="stylesheet" media="all" href="/styles/style.css">
    <script type="text/javascript" src="/scripts/jquery.js"></script>
    <script type="text/javascript" src="/scripts/main/base/base.js"></script>
    <script type="text/javascript" src="/scripts/main/base/util.js"></script>
    <script type="text/javascript" src="/scripts/main/base/event.js"></script>
    <script type="text/javascript" src="/scripts/main/base/upload.js"></script>
    <script type="text/javascript" src="/scripts/main/component/component.js"></script>
    <script type="text/javascript" src="/scripts/main/component/popup.js"></script>
    <script type="text/javascript" src="/scripts/main/component/popupLogin.js"></script>
    <script type="text/javascript" src="/scripts/main/component/upload.js"></script>
    <script type="text/javascript" src="/scripts/main/component/popupUpload.js"></script>
    <script type="text/javascript" src="/scripts/main/site/home.js"></script>

</head>
<body class="welcome_index">

<header class="navbar navbar-default navbar-static-top bs-docs-nav" id="top" role="banner">
    <div class="container">
        <div class="navbar-header">
            <button class="navbar-toggle collapsed" type="button" data-toggle="collapse" data-target=".bs-navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

            <a href="/" class="navbar-brand logo">
                <h1>头条资讯</h1>
                <h3>你关心的才是头条</h3>
            </a>
        </div>

        <nav class="collapse navbar-collapse bs-navbar-collapse" role="navigation">

            <ul class="nav navbar-nav navbar-right">

                <li class="js-login" th:if="${#response.getHeader('user') == null}"><a href="javascript:void(0);">登陆</a></li>
                <li class="js-login" th:if="${#response.getHeader('user') != null}" th:text="${#response.getHeader('user')}"><a href="javascript:void(0);">登陆</a></li>

            </ul>

        </nav>
    </div>
</header>

    <div id="main" >
        <div class="container">
            <div class="post detail">

                <div class="votebar">
                    <button class="click-like up" aria-pressed="false" title="赞同"><i class="vote-arrow"></i><span class="count">$!{news.likeCount}</span></button>
                    <button class="click-dislike down" aria-pressed="true" title="反对"><i class="vote-arrow"></i>
                    </button>
                </div>

                <div class="content" data-url="http://nowcoder.com/posts/5l3hjr">
                      <div class="content-img">
                          <img src="$!{news.image}" alt="">
                      </div>
                      <div class="content-main">
                          <h3 class="title">
                              <a target="_blank" rel="external nofollow" href="$!{news.link}">$!{news.title}</a>
                          </h3>
                          <div class="meta">
                              $!{news.link}
                              <span>
                                  <i class="fa icon-comment"></i> $!{news.commentCount}
                              </span>
                          </div>
                      </div>
                  </div>
            <div class="user-info">
                <div class="user-avatar">
                    <a href="http://nowcoder.com/u/125701"><img width="32" class="img-circle" src="${owner.headUrl}"></a>
                </div>

                </div>

                <div class="subject-name">来自 <a href="/user/$!{owner.id}">$!{owner.name}</a></div>
            </div>


            <div class="post-comment-form">
                #if($user)
                <span>评论 ($!{news.commentCount})</span>
                <form method="post" action="/addComment">
                  <div class="form-group text required comment_content">
                      <label class="text required sr-only">
                          <abbr title="required">*</abbr> 评论
                      </label>
                      <input type="hidden" name="newsId" value="$!{news.id}"/>
                      <textarea rows="5" class="text required comment-content form-control" name="content" id="content"></textarea>
                  </div>
                  <div class="text-right">
                    <input type="submit" name="commit" value="提 交" class="btn btn-default btn-info">
                  </div>
                </form>
                #else
                <div class="login-actions">
                    <a class="btn btn-success" href="/?pop=1">登录后评论</a>
                </div>
                #end
            </div>

            <div id="comments" class="comments">
                #foreach($commentvo in $comments)
                <div class="media">
                    <a class="media-left" href="http://nowcoder.com/u/210176">
                        <img src="$!{commentvo.user.headUrl}">
                    </a>
                    <div class="media-body">
                        <h4 class="media-heading"> <small class="date">$date.format('yyyy-MM-dd HH:mm:ss', $!{commentvo.comment.createdDate})</small></h4>
                        <div>$!{commentvo.comment.content}</div>
                    </div>
                </div>
                #end
            </div>

        </div>
        <script type="text/javascript">
          $(function(){

            // If really is weixin
            $(document).on('WeixinJSBridgeReady', function() {

              $('.weixin-qrcode-dropdown').show();

              var options = {
                "img_url": "",
                "link": "http://nowcoder.com/j/wt2rwy",
                "desc": "",
                "title": "读《Web 全栈工程师的自我修养》"
              };

              WeixinJSBridge.on('menu:share:appmessage', function (argv){
                WeixinJSBridge.invoke('sendAppMessage', options, function (res) {
                  // _report('send_msg', res.err_msg)
                });
              });

              WeixinJSBridge.on('menu:share:timeline', function (argv) {
                WeixinJSBridge.invoke('shareTimeline', options, function (res) {
                  // _report('send_msg', res.err_msg)
                });
              });

              // $(window).on('touchmove scroll', function() {
              //   if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
              //     $('div.backdrop').show();
              //     $('div.share-help').show();
              //   } else {
              //     $('div.backdrop').hide();
              //     $('div.share-help').hide();
              //   }
              // });

            });

          })
        </script>
    </div>

<footer>
    <div class="container">
        <p class="text-center">
            <a href="http://nowcoder.com/about">关于我们</a>
            <a href="http://nowcoder.com/download">头条客户端</a>
        </p>
        <p class="text-center">© 2013-2016 头条八卦</p>
    </div>

</footer>


</body>
</html>