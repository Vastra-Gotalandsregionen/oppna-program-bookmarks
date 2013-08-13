AUI().add('rp-bookmark-portlet',function(A) {
    var Lang = A.Lang,
        isArray = Lang.isArray,
        isFunction = Lang.isFunction,
        isNull = Lang.isNull,
        isObject = Lang.isObject,
        isString = Lang.isString,
        isUndefined = Lang.isUndefined,
        getClassName = A.ClassNameManager.getClassName,
        concat = function() {
            return Array.prototype.slice.call(arguments).join(SPACE);
        },
        
        NAME = 'rp-bookmark-portlet',
        NS = 'rp-bookmark-portlet',
        
        PORTLET_NAMESPACE = 'portletNamespace',
        PORTLET_NODE = 'portletNode',
        
        CSS_BOOKMARK_MORE_INFO = 'rp-bookmark-more-info',
        CSS_BOOKMARK_LIST_ITEM = 'rp-bookmark-item',
        CSS_BOOKMARK_MINIMIZED = 'rp-bookmark-item-minimized',
        CSS_BOOKMARK_MAXIMIZED = 'rp-bookmark-item-maximized',
        CSS_HIDDEN = 'aui-helper-hidden'
    ;
        
    var RpBookmarkPortlet = A.Component.create(
            {
                ATTRS: {
                	
                	portletNamespace: {
                		value: ''
                	},
                	
                	portletNode: {
                		setter: A.one
                	},
                	
                	someAttr: {
                		value: ''
                	}
                },
                EXTENDS: A.Component,
                NAME: NAME,
                NS: NS,
                
                prototype: {
                	
                    initializer: function(config) {
                        var instance = this;
                        
                        
                    },
                    
                    renderUI: function() {
                        var instance = this;
                    },
    
                    bindUI: function() {
                        var instance = this;
                        
                        var portletNode = instance.get(PORTLET_NODE);
                        
                        // Bind title toggle click
                        portletNode.all('a.rp-bookmark-title-toggle').on('click', instance._onTitleToggleClick, instance);
                    },
                    
                    _onTitleToggleClick: function(e) {
                    	var instance = this;

                    	e.halt();
                    	
                    	var currentTarget = e.currentTarget;
                    	
                    	var listItem = currentTarget.ancestor('.' + CSS_BOOKMARK_LIST_ITEM);
                    	
                    	if(listItem) {
                    		
                    		// Additional info is hidden -> show it
                    		if(listItem.hasClass(CSS_BOOKMARK_MINIMIZED)) {
                    			listItem.removeClass(CSS_BOOKMARK_MINIMIZED);
                    			listItem.addClass(CSS_BOOKMARK_MAXIMIZED);
                    		}

                    		// Additional info is visible -> hide it
                    		else if(listItem.hasClass(CSS_BOOKMARK_MAXIMIZED)) {
                    			listItem.removeClass(CSS_BOOKMARK_MAXIMIZED);
                    			listItem.addClass(CSS_BOOKMARK_MINIMIZED);
                    		}
                    	}
                    },
                    
                    _someFunction: function() {
                        var instance = this;
                    }

                }
            }
    );

    A.RpBookmarkPortlet = RpBookmarkPortlet;
        
    },1, {
        requires: [
	       'aui-base',
	       'aui-tooltip'
      ]
    }
);
