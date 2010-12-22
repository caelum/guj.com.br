<#macro bookmarkableLink topicId topicTitle>
${JForumContext.encodeURL('/post/${topicId}-${slugger.sluggerize(topicTitle)}', '')}
</#macro>

<#macro bookmarkablePaginatedLink topicId topicTitle page>
${JForumContext.encodeURL('/post/${topicId}-${slugger.sluggerize(topicTitle)}/${page}', '')}
</#macro>

<#macro bookmarkablePaginatedLinkAnchor topicId topicTitle page anchorTo>
${JForumContext.encodeURL('/post/${topicId}-${slugger.sluggerize(topicTitle)}/${page}#${anchorTo}', '')}
</#macro>

<#macro bookmarkablePrePost topicId topicTitle postId>
${JForumContext.encodeURL('/prepost/${topicId}/${postId}/${slugger.sluggerize(topicTitle)}', '')}
</#macro>

