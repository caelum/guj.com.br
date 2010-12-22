<#macro bookmarkableLink topicId topicTitle>
${JForumContext.encodeURL('/post/${topicId}/${slugger.sluggerize(topicTitle)}', '')}
</#macro>

<#macro bookmarkablePaginatedLink topicId topicTitle page>
${JForumContext.encodeURL('/post/${topicId}/${slugger.sluggerize(topicTitle)}?page=${page}', '')}
</#macro>
