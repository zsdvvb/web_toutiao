<#include "header.ftl">
    <div id="main">
        <div class="container">
            <ul class="letter-list">
                <#--#foreach($conversation in $conversations)-->
                <#list conversations as conversation>
                <li id="conversation-item-10005_622873">
                    <a class="letter-link" href="/msg/detail?conversationId=${conversation.message.conversationId}"></a>
                    <div class="letter-info">
                        <span class="l-time">${conversation.message.createdDate?string('yyyy-MM-dd HH:mm:ss')}</span>
                        <div class="l-operate-bar">
                            <a href="javascript:void(0);" class="sns-action-del" data-id="10005_622873">
                            删除
                            </a>
                            <a href="/msg/detail?conversationId=${conversation.message.conversationId}">
                                共${conversation.message.id}条会话
                            </a>
                        </div>
                    </div>
                    <div class="chat-headbox">
                        <span class="msg-num">
                            ${conversation.get("unread")}
                        </span>
                        <a class="list-head" href="/user/${conversation.user.id}">
                            <img alt="头像" src="${conversation.user.headUrl}">
                        </a>
                    </div>
                    <div class="letter-detail">
                        <a title="${conversation.user.name}" class="letter-name level-color-1">
                            ${conversation.user.name}
                        </a>
                        <p class="letter-brief">
                            <a href="/msg/detail?conversationId=${conversation.message.conversationId}">
                                ${conversation.message.content}
                            </a>
                        </p>
                    </div>
                </li>
                </#list>
            </ul>

        </div>
    </div>
<#include "footer.ftl">
