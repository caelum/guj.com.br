<#macro bookmarkableLink topicId topicTitle>
${JForumContext.encodeURL('/post/${topicId}/${slugger.sluggerize(topicTitle)}', '')}
</#macro>
