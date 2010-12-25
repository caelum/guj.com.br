<#macro bookmarkableLink topicId topicTitle>
${JForumContext.encodeURL('${bookmarkableUriBuilder.bookmarkableURL(topicId, topicTitle)}', '')}
</#macro>

<#macro bookmarkablePaginatedLink topicId topicTitle page>
${JForumContext.encodeURL('${bookmarkableUriBuilder.bookmarkableURL(topicId, topicTitle, page)}', '')}
</#macro>

<#macro bookmarkablePaginatedLinkAnchor topicId topicTitle page anchorTo>
${JForumContext.encodeURL('${bookmarkableUriBuilder.bookmarkableURL(topicId, topicTitle, page, anchorTo?string)}', '')}
</#macro>

<#macro bookmarkablePrePost topicId topicTitle postId>
${JForumContext.encodeURL('${bookmarkableUriBuilder.bookmarkablePrePostURL(topicId, postId, topicTitle)}', '')}
</#macro>

