////
var HttpUrlCommon ="http://localhost:8383"
//获取商品列表
var _goodsList="/goods/getGoodsList";
//修改商品
var _editGoods="/goods/operGoodsInfo?oper=edit";

var _editGoodsIsSale="/goods/operGoodsInfo?oper=isSale";
var _editGoodsSort="/goods/operGoodsInfo?oper=sort";
var _editGoodsIsAdminRecom="/goods/operGoodsInfo?oper=isAdminRecom";
//删除
var _delGoods="/goods/operGoodsInfo?oper=del";
//根据id获取商品/getGoodsById
var _getGoodsInfoById="/goods/getGoodsById";
//获取分类下拉框
var _getGoodsCate="/goods/getGoodsCategoryInfo";


//获取商品分类列表
var _getGoodsCatePageInfo="/goods/getGoodsCateInfo";
	
	///goods/getGoodsCateInfoByParentId
var _getGoodsCatePageById="/goods/getGoodsCateTopLevel";
//编辑分类
var _operEditGoodsCateInfor="/goods/operGoodsCateInfo?oper=edit";

var _operDelGoodsCateInfor="/goods/operGoodsCateInfo?oper=del";

var _operEditGoodsCateState="/goods/operGoodsCateInfo?oper=state";//状态
var _operEditGoodsCateSort="/goods/operGoodsCateInfo?oper=sort";

//根据id获取商品分类信息
var _getGoodsCateInfoById="/goods//operGoodsCateInfoByid";

//获取规格列表信息
var _getGoodsSpecList="/goods/sku/getSkuList";