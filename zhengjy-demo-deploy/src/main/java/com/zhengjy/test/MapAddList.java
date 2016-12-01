package com.zhengjy.test;


public class MapAddList {
	/*  Map<Long, List<PCGFilterConfigDto>> map= new HashMap<Long, List<PCGFilterConfigDto>>();
      
      *//** 查询商品归属商家 *//*
      List<Long> sellerIds =  new ArrayList<Long>(sellerSet);
      if(sellerIds !=null && !sellerIds.isEmpty()){
      	List<PCGSellerGoodsEntity> sellerGoods= pCGSellerGoodsService.findAllGoodsBySellerIds(sellerIds);
  		for(PCGSellerGoodsEntity sellerGood:sellerGoods){
  			pcd = new PCGFilterConfigDto();
  			pcd.setType(PlatformCouponGoodsConstant.PcgFilterConfigSellerType);
  			pcd.setTargetId(sellerGood.getSellerId());
				List<PCGFilterConfigDto> l = map.get(sellerGood.getPcgId());
				if(l==null){
					l= new ArrayList<PCGFilterConfigDto>();
					map.put(sellerGood.getPcgId(),l);
				}
				l.add(pcd);
  		}
      }*/
}
