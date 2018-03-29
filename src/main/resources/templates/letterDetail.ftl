<#include "header.ftl">
    <div id="main">
        <div class="container">
            <ul class="letter-chatlist">
                <#list messages as msg>
                <li id="msg-item-4009580">
                    <a class="list-head" href="/user/${msg.user.id}">
                        <img alt="头像" src="${msg.user.headUrl}">
                    </a>
                    <div class="tooltip fade right in">
                        <div class="tooltip-arrow"></div>
                        <div class="tooltip-inner letter-chat clearfix">
                            <div class="letter-info">
                                <p class="letter-time">${msg.message.createdDate?string('yyyy-MM-dd HH:mm:ss')}</p>
                                <a href="javascript:void(0);" id="del-link" name="4009580">删除</a>
                            </div>
                            <p class="chat-content">
                                ${msg.message.content}
                            </p>
                        </div>
                    </div>
                </li>
                </#list>
            </ul>
        </div>
    </div>
<#include "footer.ftl">