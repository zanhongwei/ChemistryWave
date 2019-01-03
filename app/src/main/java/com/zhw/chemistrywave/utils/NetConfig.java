package com.zhw.chemistrywave.utils;

/**
 * Created by Administrator on 2017/12/5.
 */

public class NetConfig {
    //国际站的服务器地址
    public final static String baseurl = "http://www.harlanchina.com/";//国际站服务器地址
//    public final static String baseurl = "http://157.10.1.103/";//陈继伟本地

    //验证码
    public final static String sendcode = baseurl + "log/mobileCode";
    //注册(采购商)
    public final static String register = baseurl + "log/register";
    //注册(供货商)
    public final static String register2 = baseurl + "user/update";
    //登录
    public final static String login = baseurl + "log/login";
    //修改登录密码
    public final static String changepwd = baseurl + "user/password/modify";
    //修改手机验证码
    public final static String changephonecode = baseurl + "user/mobile/code";
    //修改手机号
    public final static String changephone = baseurl + "user/update";
    //实名认证
    public final static String namecongif = baseurl + "realNameAuth/save";
    //实名认证详情
    public final static String namecongifdetail = baseurl + "realNameAuth/detail";
    //add地址
    public final static String addaddress = baseurl + "address/save";
    //地址列表
    public final static String addresslist = baseurl + "address/list";
    //修改默认地址
    public final static String defaultaddress = baseurl + "address/update";
    //删除地址
    public final static String deleteaddress = baseurl + "address/delete";
    //修改地址
    public final static String changeaddress = baseurl + "address/update";
    //实单竞价货物基本信息
    public final static String sdjj_goodsbaseinfo = baseurl + "cargo/save";
    //实单竞价货物基本信息
    public final static String sdjj_qualitydetail = baseurl + "quality/save";
    //忘记密码验证码
    public final static String forpwdcode = baseurl + "log/mobile";
    //忘记密码修改密码
    public final static String resetpwd = baseurl + "log/resetPwd";
    //发起
    public final static String faqi = baseurl + "sponsor/save";
    //品质详情保存
    public final static String qualitysave = baseurl + "quality/save";
    //我发起的竞价
    public final static String myInvokebid = baseurl + "bid/myInvoke";
    //我参与的竞价详情
    public final static String biddetailInvoke = baseurl + "bid/detailInvoke";
    //查看参与竞标者
    public final static String bidderInformation = baseurl + "bid/bidderInformation";
    //参与竞价详情
    public final static String bidderDetail = baseurl + "bid/bidderDetail";

//--------------------------------------------------------------------------------------------

    //上传文件的接口
    public final static String SAVE_FILE = baseurl + "file/upload";
    //系统消息
    public final static String sysmsg = baseurl + "msg/sys/list";
    //通知消息
    public final static String notificationmsg = baseurl + "msg/note/list";
    //保存记录
    public final static String baocunjilu = baseurl + "sponsor/save";
    //上传文件-货物基本信息-实单采购
    public final static String saveFile = baseurl + "cargo/upload";
    //实单采购----1.保存货物基本信息
    public final static String save_goodsbaseinfo = baseurl + "cargo/save";
    //实单采购----1.修改货物基本信息
    public final static String update_goodsbaseinfo = baseurl + "cargo/update";
    //实单采购----2.保存货物品质详情
    public final static String save_qualitydetail = baseurl + "quality/save";
    //实单采购----2.修改货物品质详情
    public final static String update_qualitydetail = baseurl + "quality/update";
    //实单采购----2.保存货物金属含量
    public final static String save_metal = baseurl + "metal/save";
    //2.修改货物金属含量
    public final static String update_metal = baseurl + "metal/update";
    //实单采购----2.保存货物检测报告
    public final static String save_report = baseurl + "report/save";
    //实单采购----查询所有订单列表
    public final static String shidancaifou_allorder = baseurl + "sponsor/list";
    //实单采购----查询指定订单的订单项
    public final static String shidancaifou_appointorder = baseurl + "sponsor/detail";
    //实单采购----查询订单项详情
    public final static String purchasedetail = baseurl + "cargo/detail";
    //实单采购-发布采购流程或者保存在草稿箱
    public final static String fabucaigou = baseurl + "sponsor/publish";
    //5.参与采购-填写订单项需求（cj）
    public final static String canyucaigou = baseurl + "prtp/save";
    //查询平时购物的商品
    public final static String pingshigoumaisp = baseurl + "shopIntent/list";
    //一键采购
    public final static String onekeycaigou = baseurl + "recpur/saveBatch";
    //删除此订单
    public final static String delete_purchase = baseurl + "sponsor/delete";
    //查询货物详情（M）
    public final static String purchase_detail = baseurl + "cargo/detail";
    //查询金属详情(M
    public final static String metal_detail = baseurl + "metal/detail";
    //查询品质详情(M)
    public final static String quality_detail = baseurl + "quality/detail";
    //删除此商品
    public final static String cargo_delete = baseurl + "cargo/delete";
    //修改检测报告-上传文件
    public final static String report_upload = baseurl + "report/upload";
    //修改检测报告
    public final static String report_update = baseurl + "report/update";

    //查看一键采购者
    public final static String onekey_caigouz = baseurl + "prtp/oneKeys";
    //查看个人资料
    public final static String user_detail = baseurl + "user/detail";
    //修改个人资料
    public final static String user_update = baseurl + "user/update";
    //查看采购者
    public final static String caigouzhe_list = baseurl + "prtp/loneKey";
    //我参与的采购
    public final static String canyucaigou_list = baseurl + "pur/myInvoke";
    //参与采购-查询指定订单
    public final static String canyucaigou_appointlist = baseurl + "prtp/detail";
    //我发起的采购
    public final static String wofaqidecaigou = baseurl + "pur/myPublish";
    //选择他
    public final static String choosehim = baseurl + "prtp/updateChoseHim";
    //生成订单
    public final static String make_order = baseurl + "order/buyItNow";
    //取消订单
    public final static String Cancel_order = baseurl + "order/cancel";
    //免费监理
    public final static String mianfeijianli = baseurl + "freeSupervision/save";
    //三方检测
    public final static String sanfangjiance = baseurl + "report/save";
    //订单列表
    public final static String order_list = baseurl + "order/purchaseOrders";
    //供货订单列表
    public final static String ghorder_list = baseurl + "order/supplyOrders";
    //我的免费找货
    public final static String mianfeizhaohuo_list = baseurl + "freeFindGood/list";
    //我的商品
    public final static String mygoods_list = baseurl + "mallShop/myProds";
    //确认收货
    public final static String querenshouhuo_url = baseurl + "order/confirmReceipt";
    //首页-点击以货易货(查询所有发起的易货易货列表)
    public final static String yihuoyihuo_url = baseurl + "barter/list";
    //自营商城
    public final static String ziyingshangcheng_url = baseurl + "/goodsMall/list";
    //自营秒杀商品
    public final static String ziyingshangchengmiaosha_url = baseurl + "Merchandise/list";
    //自营折扣商品
    public final static String ziyingshangchengZhekou_url = baseurl + "Merchandise/zhelist";
    //商品详情
    public final static String goodsdetail_url = baseurl + "/goodsMall/detail";
    //我要开店
    public final static String woyaokaidian_url = baseurl + "shop/openShop";
    //修改开店信息
    public final static String woyaokaidian_urls = baseurl + "shop/alter";
    //现货供应
    public final static String xianhuogongying_url = baseurl + "Merchandise/list";
    //添加商品
    public final static String addgoods_url = baseurl + "Merchandise/appsave";
    //添加商品--修改商品
    public final static String modifygoods_url = baseurl + "Merchandise/appadds";
    //我的商品
    public final static String mygoods_url = baseurl + "/goodsMall/list";
    //商品下架
    public final static String mygoodsxiajia_url = baseurl + "goodsMall/offShelve";
    //商品上架
    public final static String mygoodsshangjia_url = baseurl + "goodsMall/onShelve";
    //删除商品
    public final static String mygoodsdelete_url = baseurl + "Merchandise/delete";
    /**
     * 以货易货
     */
    //查询指定易货单号、指定参与人 填写的易货列表
    public final static String yihuolist_url = baseurl + "barter/participations";
    //以货易货--删除
    public final static String yihuodelete_url = baseurl + "barter/delete";
    //指定id查看详情(M)
    public final static String yihuodetail_url = baseurl + "barter/sinDetail";
    public final static String yihuodetailist_url = baseurl + "barter/participations";
    // 9.3首页-以货易货-查看详情(M)
    public final static String homeyihuodetail_url = baseurl + "barter/detail";
    //查询指定易货单号、指定参与人 填写的易货列表
    public final static String wocanyudeyihuo_url = baseurl + "barter/myInvokeList";
    //我发布的易货
    public final static String wofaqideyihuo_url = baseurl + "barter/myPublishList";
    //发起易货-保存
    public final static String faqiyihuosave_url = baseurl + "barter/launch";
    //发起易货=修改
    public final static String faqiyihuoupdate_url = baseurl + "barter/update";
    //发布易货
    public final static String fabuyihuo_url = baseurl + "barter/publish";
    //9.5点击参与易货预处理(M
    public final static String ifcanyuyihuo_url = baseurl + "barter/preParticipate";
    //参与易货
    public final static String canyuyihuo_url = baseurl + "barter/participate";
    //三方检测
    public final static String sanfangjiance_url = baseurl + "detection/save";
    //查看所有的申请三方检测的列表(M)
    public final static String sanfangjiancelist_url = baseurl + "/orderCheck/detail";
    //我的收藏
    public final static String mylove_url = baseurl + "collection/list";
    //添加收藏
    public final static String savelove_url = baseurl + "collection/save";
    //公告快讯
    public final static String gonggao_url = baseurl + "newsFlash/list";
    // 6.查询我的购物车列表(M)
    public final static String shopcar_url = baseurl + "cart/myList";
    //最新报价
    public final static String zuixinbaojia_url = baseurl + "Merchandise/guessYouLike";

    //--------------------------------------------------------------------
    //
//                                  _oo8oo_
//                                 o8888888o
//                                 88" . "88
//                                 (| -_- |)
//                                 0\  =  /0
//                               ___/'==='\___
//                             .' \\|     |// '.
//                            / \\|||  :  |||// \
//                           / _||||| -:- |||||_ \
//                          |   | \\\  -  /// |   |
//                          | \_|  ''\---/''  |_/ |
//                          \  .-\__  '-'  __/-.  /
//                        ___'. .'  /--.--\  '. .'___
//                     ."" '<  '.___\_<|>_/___.'  >' "".
//                    | | :  `- \`.:`\ _ /`:.`/ -`  : | |
//                    \  \ `-.   \_ __\ /__ _/   .-` /  /
//                =====`-.____`.___ \_____/ ___.`____.-`=====
//                                  `=---=`
//
//
//       ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//              佛祖保佑                                代码无Bug

    //昝宏伟-----------------------start-----------------------------

    public final static String SHOPING_TREND = baseurl + "product/list";
    //接口名称: 8.banner轮播列表
    //get advertised banner image
    public final static String ADVERTISED_BANNER = baseurl + "banner/list";
    //接口名称: 4.搜索供应商
    public final static String SEARCH_SUPPLIER = baseurl + "shop/listByName";
    //接口名称: 5.五大服务（我申请的）
    public final static String MYSERVICE = baseurl + "fiveSer/pager";
    //接口名称: 11.行业资讯列表
    public static final String TRADE_NEWS = baseurl + "newsFlash/list";
    //接口名称: 16.店铺详情
    public static final String SHOP_DETAIL = baseurl + "shop/detail";
    //添加商品的借口
    public static final String ADD_GOODS = baseurl + "goodsMall/save";
    //修改商品的借口
    public static final String EDIT_GOODS = baseurl + "goodsMall/update";
    //系统询盘的滚动列表/待处理询盘
    public static final String UNHANDLE_XUNPAN = baseurl + "tarEnquiry/pager";
    //----------------------------需要检查的接口  start -------------------------------------
    //暂时用不到的接口
    //广告列表
    public final static String ADVERYISING_INFO = baseurl + "expo/list";
    //接口名称: 13.产品详情
    public static final String GOODS_DETAIL = baseurl + "goodsMall/detail";
    //供应商询盘
    public static final String gongyingshangxunpan = baseurl + "tarEnquiry/pager";
    //添加供应商询盘
    public static final String tianjiagongyingshangxunpan = baseurl + "tarEnquiry/save";
    //添加购物车
    public static final String tianjiagouwuche = baseurl + "cart/save";
    //添加公司环境照片
    public static final String tianjiagongsi = baseurl + "shopEnv/save";
    //查询公司环境照片
    public static final String getComEnvironment = baseurl + "shopEnv/page";
    //修改公司环境照片
    public static final String ChangeComEnvironment = baseurl + "shopEnv/update";
    //

    //-------------------------------需要检查的接口 end -------------------------------------

    //昝宏伟-----------------------end----------------------------
    //删除公司环境照片
    public static final String DelectComEnvironment = baseurl + "shopEnv/delete";
    //购物车更新商品数量的接口
    public static final String cart_update = baseurl + "cart/update";
    //get price express list
    public static String PRICE_EXPRESS = baseurl + "price/list";
    //get hot-sale list
    public static String HOT_SALE = baseurl + "goodsMall/list";
    //get hot-supplier list
    public static String HOT_SUPPLIER = baseurl + "shop/appHotSuppliers";
    //this five service
    public static String FIVE_SERVICE = baseurl + "fiveSer/save";
    //接口名称: 3.搜索商品
    public static String SEARCG_GOODS = baseurl + "goodsMall/listByName";
    //免费询盘
    public static String FIND = baseurl + "goodsMall/save";
    //6.产品分类
    public static String SORT_GOODS = baseurl + "category/recursion";
    //接口名称: 9.banner详情
    public static String BANNER_DETAILS = baseurl + "cmsBanner/detail";
    //接口名称: 7.一级分类下热销产品列表
    public static String FIRST_SORTS_GOODS = baseurl + "goodsMall/list";
    //接口名称: 7.1点击二级分类后的商品列表
    public static String SECOND_SORTS_GOODS = baseurl + "goodsMall/list";

}
