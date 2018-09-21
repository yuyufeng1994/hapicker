// JavaScript Document
/**!
 * 寰俊鍐呯疆娴忚鍣ㄧ殑Javascript API锛屽姛鑳藉寘鎷細
 *
 * 1銆佸垎浜埌寰俊鏈嬪弸鍦�
 * 2銆佸垎浜粰寰俊濂藉弸
 * 3銆佸垎浜埌鑵捐寰崥
 * 4銆侀殣钘�/鏄剧ず鍙充笂瑙掔殑鑿滃崟鍏ュ彛
 * 5銆侀殣钘�/鏄剧ず搴曢儴娴忚鍣ㄥ伐鍏锋爮
 * 6銆佽幏鍙栧綋鍓嶇殑缃戠粶鐘舵€�
 * 7銆佽皟璧峰井淇″鎴风鐨勫浘鐗囨挱鏀剧粍浠�
 * 8銆佸叧闂叕浼楀钩鍙癢eb椤甸潰
 *
 * @author zhaoxianlie(http://www.baidufe.com)
 */
var WeixinApi = (function () {

    "use strict";

    /**
     * 鍒嗕韩鍒板井淇℃湅鍙嬪湀
     * @param       {Object}    data       寰呭垎浜殑淇℃伅
     * @p-config    {String}    appId      鍏紬骞冲彴鐨刟ppId锛堟湇鍔″彿鍙敤锛�
     * @p-config    {String}    imageUrl   鍥剧墖鍦板潃
     * @p-config    {String}    link       閾炬帴鍦板潃
     * @p-config    {String}    desc       鎻忚堪
     * @p-config    {String}    title      鍒嗕韩鐨勬爣棰�
     *
     * @param       {Object}    callbacks  鐩稿叧鍥炶皟鏂规硶
     * @p-config    {Boolean}   async                   ready鏂规硶鏄惁闇€瑕佸紓姝ユ墽琛岋紝榛樿false
     * @p-config    {Function}  ready(argv)             灏辩华鐘舵€�
     * @p-config    {Function}  dataLoaded(data)        鏁版嵁鍔犺浇瀹屾垚鍚庤皟鐢紝async涓簍rue鏃舵湁鐢紝涔熷彲浠ヤ负绌�
     * @p-config    {Function}  cancel(resp)    鍙栨秷
     * @p-config    {Function}  fail(resp)      澶辫触
     * @p-config    {Function}  confirm(resp)   鎴愬姛
     * @p-config    {Function}  all(resp)       鏃犺鎴愬姛澶辫触閮戒細鎵ц鐨勫洖璋�
     */
    function weixinShareTimeline(data, callbacks) {
        callbacks = callbacks || {};
        var shareTimeline = function (theData) {
            WeixinJSBridge.invoke('shareTimeline', {
                "appid":theData.appId ? theData.appId : '',
                "img_url":theData.imgUrl,
                "link":theData.link,
                "desc":theData.title,
                "title":theData.desc, // 娉ㄦ剰杩欓噷瑕佸垎浜嚭鍘荤殑鍐呭鏄痙esc
                "img_width":"120",
                "img_height":"120"
            }, function (resp) {
                switch (resp.err_msg) {
                    // share_timeline:cancel 鐢ㄦ埛鍙栨秷
                    case 'share_timeline:cancel':
                        callbacks.cancel && callbacks.cancel(resp);
                        break;
                    // share_timeline:fail銆€鍙戦€佸け璐�
                    case 'share_timeline:fail':
                        callbacks.fail && callbacks.fail(resp);
                        break;
                    // share_timeline:confirm 鍙戦€佹垚鍔�
                    case 'share_timeline:confirm':
                    case 'share_timeline:ok':
                        callbacks.confirm && callbacks.confirm(resp);
                        break;
                }
                // 鏃犺鎴愬姛澶辫触閮戒細鎵ц鐨勫洖璋�
                callbacks.all && callbacks.all(resp);
            });
        };
        WeixinJSBridge.on('menu:share:timeline', function (argv) {
            if (callbacks.async && callbacks.ready) {
                window["_wx_loadedCb_"] = callbacks.dataLoaded || new Function();
                if(window["_wx_loadedCb_"].toString().indexOf("_wx_loadedCb_") > 0) {
                    window["_wx_loadedCb_"] = new Function();
                }
                callbacks.dataLoaded = function (newData) {
                    window["_wx_loadedCb_"](newData);
                    shareTimeline(newData);
                };
                // 鐒跺悗灏辩华
                callbacks.ready && callbacks.ready(argv);
            } else {
                // 灏辩华鐘舵€�
                callbacks.ready && callbacks.ready(argv);
                shareTimeline(data);
            }
        });
    }

    /**
     * 鍙戦€佺粰寰俊涓婄殑濂藉弸
     * @param       {Object}    data       寰呭垎浜殑淇℃伅
     * @p-config    {String}    appId      鍏紬骞冲彴鐨刟ppId锛堟湇鍔″彿鍙敤锛�
     * @p-config    {String}    imageUrl   鍥剧墖鍦板潃
     * @p-config    {String}    link       閾炬帴鍦板潃
     * @p-config    {String}    desc       鎻忚堪
     * @p-config    {String}    title      鍒嗕韩鐨勬爣棰�
     *
     * @param       {Object}    callbacks  鐩稿叧鍥炶皟鏂规硶
     * @p-config    {Boolean}   async                   ready鏂规硶鏄惁闇€瑕佸紓姝ユ墽琛岋紝榛樿false
     * @p-config    {Function}  ready(argv)             灏辩华鐘舵€�
     * @p-config    {Function}  dataLoaded(data)        鏁版嵁鍔犺浇瀹屾垚鍚庤皟鐢紝async涓簍rue鏃舵湁鐢紝涔熷彲浠ヤ负绌�
     * @p-config    {Function}  cancel(resp)    鍙栨秷
     * @p-config    {Function}  fail(resp)      澶辫触
     * @p-config    {Function}  confirm(resp)   鎴愬姛
     * @p-config    {Function}  all(resp)       鏃犺鎴愬姛澶辫触閮戒細鎵ц鐨勫洖璋�
     */
    function weixinSendAppMessage(data, callbacks) {
        callbacks = callbacks || {};
        var sendAppMessage = function (theData) {
            WeixinJSBridge.invoke('sendAppMessage', {
                "appid":theData.appId ? theData.appId : '',
                "img_url":theData.imgUrl,
                "link":theData.link,
                "desc":theData.desc,
                "title":theData.title,
                "img_width":"120",
                "img_height":"120"
            }, function (resp) {
                switch (resp.err_msg) {
                    // send_app_msg:cancel 鐢ㄦ埛鍙栨秷
                    case 'send_app_msg:cancel':
                        callbacks.cancel && callbacks.cancel(resp);
                        break;
                    // send_app_msg:fail銆€鍙戦€佸け璐�
                    case 'send_app_msg:fail':
                        callbacks.fail && callbacks.fail(resp);
                        break;
                    // send_app_msg:confirm 鍙戦€佹垚鍔�
                    case 'send_app_msg:confirm':
                    case 'send_app_msg:ok':
                        callbacks.confirm && callbacks.confirm(resp);
                        break;
                }
                // 鏃犺鎴愬姛澶辫触閮戒細鎵ц鐨勫洖璋�
                callbacks.all && callbacks.all(resp);
            });
        };
        WeixinJSBridge.on('menu:share:appmessage', function (argv) {
            if (callbacks.async && callbacks.ready) {
                window["_wx_loadedCb_"] = callbacks.dataLoaded || new Function();
                if(window["_wx_loadedCb_"].toString().indexOf("_wx_loadedCb_") > 0) {
                    window["_wx_loadedCb_"] = new Function();
                }
                callbacks.dataLoaded = function (newData) {
                    window["_wx_loadedCb_"](newData);
                    sendAppMessage(newData);
                };
                // 鐒跺悗灏辩华
                callbacks.ready && callbacks.ready(argv);
            } else {
                // 灏辩华鐘舵€�
                callbacks.ready && callbacks.ready(argv);
                sendAppMessage(data);
            }
        });
    }

    /**
     * 鍒嗕韩鍒拌吘璁井鍗�
     * @param       {Object}    data       寰呭垎浜殑淇℃伅
     * @p-config    {String}    imageUrl   鍥剧墖鍦板潃
     * @p-config    {String}    link       閾炬帴鍦板潃
     * @p-config    {String}    desc       鎻忚堪
     * @p-config    {String}    title      鍒嗕韩鐨勬爣棰�
     *
     * @param       {Object}    callbacks  鐩稿叧鍥炶皟鏂规硶
     * @p-config    {Boolean}   async                   ready鏂规硶鏄惁闇€瑕佸紓姝ユ墽琛岋紝榛樿false
     * @p-config    {Function}  ready(argv)             灏辩华鐘舵€�
     * @p-config    {Function}  dataLoaded(data)        鏁版嵁鍔犺浇瀹屾垚鍚庤皟鐢紝async涓簍rue鏃舵湁鐢紝涔熷彲浠ヤ负绌�
     * @p-config    {Function}  cancel(resp)    鍙栨秷
     * @p-config    {Function}  fail(resp)      澶辫触
     * @p-config    {Function}  confirm(resp)   鎴愬姛
     * @p-config    {Function}  all(resp)       鏃犺鎴愬姛澶辫触閮戒細鎵ц鐨勫洖璋�
     */
    function weixinShareWeibo(data, callbacks) {
        callbacks = callbacks || {};
        var shareWeibo = function (theData) {
            WeixinJSBridge.invoke('shareWeibo', {
                "content":theData.desc,
                "link":theData.link,
                "img_url":theData.imgUrl,
                "title":theData.title,
                "img_width":"120",
                "img_height":"120"
            }, function (resp) {
                switch (resp.err_msg) {
                    // share_weibo:cancel 鐢ㄦ埛鍙栨秷
                    case 'share_weibo:cancel':
                        callbacks.cancel && callbacks.cancel(resp);
                        break;
                    // share_weibo:fail銆€鍙戦€佸け璐�
                    case 'share_weibo:fail':
                        callbacks.fail && callbacks.fail(resp);
                        break;
                    // share_weibo:confirm 鍙戦€佹垚鍔�
                    case 'share_weibo:confirm':
                    case 'share_weibo:ok':
                        callbacks.confirm && callbacks.confirm(resp);
                        break;
                }
                // 鏃犺鎴愬姛澶辫触閮戒細鎵ц鐨勫洖璋�
                callbacks.all && callbacks.all(resp);
            });
        };
        WeixinJSBridge.on('menu:share:weibo', function (argv) {
            if (callbacks.async && callbacks.ready) {
                window["_wx_loadedCb_"] = callbacks.dataLoaded || new Function();
                if(window["_wx_loadedCb_"].toString().indexOf("_wx_loadedCb_") > 0) {
                    window["_wx_loadedCb_"] = new Function();
                }
                callbacks.dataLoaded = function (newData) {
                    window["_wx_loadedCb_"](newData);
                    shareWeibo(newData);
                };
                // 鐒跺悗灏辩华
                callbacks.ready && callbacks.ready(argv);
            } else {
                // 灏辩华鐘舵€�
                callbacks.ready && callbacks.ready(argv);
                shareWeibo(data);
            }
        });
    }

    /**
     * 璋冭捣寰俊Native鐨勫浘鐗囨挱鏀剧粍浠躲€�
     * 杩欓噷蹇呴』瀵瑰弬鏁拌繘琛屽己妫€娴嬶紝濡傛灉鍙傛暟涓嶅悎娉曪紝鐩存帴浼氬鑷村井淇″鎴风crash
     *
     * @param {String} curSrc 褰撳墠鎾斁鐨勫浘鐗囧湴鍧€
     * @param {Array} srcList 鍥剧墖鍦板潃鍒楄〃
     */
    function imagePreview(curSrc,srcList) {
        if(!curSrc || !srcList || srcList.length == 0) {
            return;
        }
        WeixinJSBridge.invoke('imagePreview', {
            'current' : curSrc,
            'urls' : srcList
        });
    }

    /**
     * 鏄剧ず缃戦〉鍙充笂瑙掔殑鎸夐挳
     */
    function showOptionMenu() {
        WeixinJSBridge.call('showOptionMenu');
    }


    /**
     * 闅愯棌缃戦〉鍙充笂瑙掔殑鎸夐挳
     */
    function hideOptionMenu() {
        WeixinJSBridge.call('hideOptionMenu');
    }

    /**
     * 鏄剧ず搴曢儴宸ュ叿鏍�
     */
    function showToolbar() {
        WeixinJSBridge.call('showToolbar');
    }

    /**
     * 闅愯棌搴曢儴宸ュ叿鏍�
     */
    function hideToolbar() {
        WeixinJSBridge.call('hideToolbar');
    }

    /**
     * 杩斿洖濡備笅鍑犵绫诲瀷锛�
     *
     * network_type:wifi     wifi缃戠粶
     * network_type:edge     闈瀢ifi,鍖呭惈3G/2G
     * network_type:fail     缃戠粶鏂紑杩炴帴
     * network_type:wwan     2g鎴栬€�3g
     *
     * 浣跨敤鏂规硶锛�
     * WeixinApi.getNetworkType(function(networkType){
     *
     * });
     *
     * @param callback
     */
    function getNetworkType(callback) {
        if (callback && typeof callback == 'function') {
            WeixinJSBridge.invoke('getNetworkType', {}, function (e) {
                // 鍦ㄨ繖閲屾嬁鍒癳.err_msg锛岃繖閲岄潰灏卞寘鍚簡鎵€鏈夌殑缃戠粶绫诲瀷
                callback(e.err_msg);
            });
        }
    }

    /**
     * 鍏抽棴褰撳墠寰俊鍏紬骞冲彴椤甸潰
     */
    function closeWindow() {
        WeixinJSBridge.call("closeWindow");
    }

    /**
     * 褰撻〉闈㈠姞杞藉畬姣曞悗鎵ц锛屼娇鐢ㄦ柟娉曪細
     * WeixinApi.ready(function(Api){
     *     // 浠庤繖閲屽彧鐢ˋpi鍗虫槸WeixinApi
     * });
     * @param readyCallback
     */
    function wxJsBridgeReady(readyCallback) {
        if (readyCallback && typeof readyCallback == 'function') {
            var Api = this;
            var wxReadyFunc = function () {
                readyCallback(Api);
            };
            if (typeof window.WeixinJSBridge == "undefined"){
                if (document.addEventListener) {
                    document.addEventListener('WeixinJSBridgeReady', wxReadyFunc, false);
                } else if (document.attachEvent) {
                    document.attachEvent('WeixinJSBridgeReady', wxReadyFunc);
                    document.attachEvent('onWeixinJSBridgeReady', wxReadyFunc);
                }
            }else{
                wxReadyFunc();
            }
        }
    }

    return {
        version         :"1.6",
        ready           :wxJsBridgeReady,
        shareToTimeline :weixinShareTimeline,
        shareToWeibo    :weixinShareWeibo,
        shareToFriend   :weixinSendAppMessage,
        showOptionMenu  :showOptionMenu,
        hideOptionMenu  :hideOptionMenu,
        showToolbar     :showToolbar,
        hideToolbar     :hideToolbar,
        getNetworkType  :getNetworkType,
        imagePreview    :imagePreview,
        closeWindow     :closeWindow
    };
})();