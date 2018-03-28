<#include "header.ftl">

    <div id="main">


        <div class="container" id="daily">
            <div class="jscroll-inner">
                <div class="daily">
                    <#list vos as vo>
                        <#list vo as nu>
                            <#if nu_index == 0>
                                <div class = "posts">
                                    <h3 class="date">
                                        <i class="fa icon-calendar"></i>
                                        <span>头条资讯 ${nu.getNews().createdDate?string('yyyy-MM-dd')}</span>
                                    </h3>
                                </div>
                            </#if>
                                <div class="post">
                                    <div class="votebar">
                                        <button class="click-like up" aria-pressed="false" title="赞同"><i class="vote-arrow"></i><span class="count">${nu.news.likeCount}</span></button>
                                        <button class="click-dislike down" aria-pressed="true" title="反对"><i class="vote-arrow"></i>
                                        </button>
                                    </div>
                                    <div class="content" data-url="http://nowcoder.com/posts/5l3hjr">
                                        <div >
                                            <#--进行在线切图-->
                                            <#--<img class="content-img" src=${nu.getNews().getImage()}-news_pic alt="">-->
                                            <img class="content-img" src=${nu.getNews().getImage()} alt="">
                                        </div>
                                        <div class="content-main">
                                            <h3 class="title">
                                                <a target="_blank" rel="external nofollow" href="${nu.getNews().getLink()}">${nu.getNews().title}</a>
                                            </h3>
                                            <div class="meta">
                                                <p>${nu.getNews().getCreatedDate()?string('YY/MM/dd hh:mm:ss')}</p>
                                                <span>
                                                    <i class="fa icon-comment"></i> <p>${nu.news.commentCount}</p>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="user-info">
                                        <div class="user-avatar">
                                            <a href="/user/${nu.user.id}"><img width="32" class="img-circle" src="${nu.user.headUrl}"></a>
                                        </div>

                                    </div>

                                    <div class="subject-name">来自 <a href="/user/${nu.user.id}">${nu.user.name}</a></div>
                                </div>
                        </#list>
                    </#list>


                </div>
            </div>
        </div>

    </div>


<#if pop?exists>
<#if pop == 1>
<script>
window.loginpop = ${pop};
</script>
</#if>
</#if>

<#include "footer.ftl">