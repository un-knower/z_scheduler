package z_bigdata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class A {

	public static void main(String[] args) {
		String str="coord_mDaKdjGoodsRankDay , coord_mDaKdjLogisticsKpi , coord_mDaKdjMissingOrder , coord_mDaKdjOrderDeliver , coord_mDaKdjMulitiDay , coord_mSrEgGoodsEvaPicNum , coord_mSrEgApiSecRecode , coord_mUecFlowTrendWeek , coord_mUecFlowMobileWeek , coord_mUecFlowTrendDay , coord_mDaGoodsList , coord_mUecJrPaymetcountJr , coord_mSrEgGoodsEvaWords , coord_mSrEgGoodsEvaRatio , coord_DimSysProduct , coord_mUecJqaddMember , coord_mUecGoodsQqgSkuPcm , coord_mUecGoodsQqgPcm , coord_mWxMemberLl , coord_mUecZywlKpiWl , coord_mUecSxdOrdertopOrder , coord_mUecSxdBusinessindexOrder , coord_mUecSxdAllareatopOrder , coord_mUecSjSevenaddSj , coord_mUecRrhLl , coord_mUecProductOverviewPcm , coord_mUecPowerDataPower , coord_mUecMemberAllinfoMember , coord_mUecLevelupMember , coord_mUecKdjwlKpiWl , coord_mUecJrPaymetJr , coord_mUecJrAllJr , coord_mUecGoodsSxPcm , coord_mUecGoodsSscPcm , coord_mUecGoodsSkuPcm , coord_mUecGoodscateSkuPcm , coord_mUecGoodsbrandSkuPcm , coord_mUecGooddaypayPcm , coord_mUecFansgenderSj , coord_mUecCoreBusinessOrder , coord_mUecChannelcountMember , coord_mUecBldjStoreTopsixOrder , coord_mUecBldjGoodsTopsixOrder , coord_mUecBldjBusinessindexOrder , coord_mUecBkAllcountOrder , coord_mUecAllCountOrder , coord_mSrEgGoodsEvaNum , coord_mSrEgGoodsEvaScore , coord_mSrEgKeywordsPvUv , coord_mSrEgGoodsNumAmount , coord_mSrEgGoodsNumAmount , coord_mSrEgGoodsPvUv , coord_mDaUndeliverOrderd , coord_mUecChannelDayOrder , coord_Ph2ChannelAnalysisOrder , coord_mMemMktDashboardInfo , coord_mDaMissingOrderd , coord_DimChannelAnalysis , coord_Ph2ChannelAnalysisPv , coord_Ph2ChannelAnalysisUv , coord_Ph2ChannelPvDaliy , coord_Ph2ChannelUvDaliy , coord_mDaUnusualOrder , coord_mDaAbnDaily , coord_mDaAbnHour , coord_GadSalePayDay , coord_GadSaleCateDay , coord_mMemConMemberInfo , coord_mMemPointUseInfo , coord_mMemRegMemberInfo , coord_mMaktChanUvDay , coord_mOperHdOrderPropDay , coord_mOperCoreOrderDay , coord_mOperHdLogistDay , coord_mOperHdOrderDistrDay , coord_mMaktChanBounceDay , coord_mMaktChanOrderDay , coord_mDaStockOl , coord_mDaMemReg , coord_mDaMissingOrder , coord_mDaOrder , coord_mDaLogisticsNormaltd , coord_mOperGoodsRankDaily , coord_mOperCateLev1Sku , coord_mOperBrandRankDaily , coord_mOperCateRankDaily , coord_mOperCateOrderDaily , coord_mOperPartOrderDaily , coord_mSocialRenrenSummary , coord_mOperXdLogisticsNormalt , coord_mSocialRenrenFansReg , coord_mSocialRenrenFirstOrder , coord_mOperSummaryUvDaily , coord_mOperSumRephase30Daily , coord_mOperXdLogisticsFinish , coord_mOperPartTranDaily , coord_mOperSummaryMemberDaily , coord_mOperSummaryOrderDaily , coord_mOperLhLogisticsFinish , coord_mOperLhLogisticsFreeze , coord_mOperLhLogisticsNormalt , coord_mOperKpiSaleMoneyAll , coord_mOperKpiSkuOl , coord_mOperKpiSkuOlAll , coord_mOperKpiMemberCount , coord_mOperKpiSaleMoney , coord_mOperDailyOperSummaryc , coord_mOperDailyOperSummaryd , coord_mOperDailySku , coord_mOperDailySkuOrder , coord_mOperComRephase30Daily , coord_mOperDailyLogisticsDetail , coord_DimSysProduct , coord_mComProductCoreMemberCt , coord_mComProductCoreSkuCount , coord_DimSysOrder ,";
		String[] split = str.split(",");
		int count=0;
		System.out.println("1111111111111111111111111111111");
		for (String item : split) {
			count++;
			if(count%5==0){
				System.out.println();
			}
			System.out.print(item.trim()+"   ");
		}
		System.out.println();
		System.out.println("2222222222222222222222222222222");
		count=0;
		for (String item : split) {
			String item_upper = item.toUpperCase();
			if(item_upper.contains("UEC")){
				count++;
				if(count%5==0){
					System.out.println();
				}
				System.out.print(item+"   ");
			}
		}
		System.out.println();
		System.out.println("3333333333333333333333333333333");
		count=0;
		List<String> table_list = new ArrayList<String>();
		for (String item : split) {
			item = item.trim();
			String item_upper = item.toUpperCase();
			if(!item_upper.contains("UEC")){
			//	System.out.print(changeChars(item)+"   ");
				table_list.add(changeChars(item));
			}
			Collections.sort(table_list);
		}
		for (String table : table_list) {
			count++;
			System.out.print(table+"     ");
			if(count%5==0){
				System.out.println();
				System.out.println(count/5);
			}
		}
		
		System.out.println(table_list.size()+"------------------");
		System.out.println();
		System.out.println("4444444444444444444444444444444");
		count=0;
		table_list = new ArrayList<String>();
		for (String item : split) {
			item = item.trim();
			String item_upper = item.toUpperCase();
			if(item_upper.contains("UEC")){
			//	System.out.print(changeChars(item)+"   ");
				table_list.add(changeChars(item));
			}
			Collections.sort(table_list);
		}
		for (String table : table_list) {
			count++;
			System.out.print(table+"     ");
			if(count%5==0){
				System.out.println();
				System.out.println(count/5);
			}
		}
		
		System.out.println(table_list.size()+"------------------");
		System.out.println();
		System.out.println("5555555555555555555555555555555");
		System.out.println();
		System.out.println("6666666666666666666666666666666");
		System.out.println();
		System.out.println("7777777777777777777777777777777");
		System.out.println();
		System.out.println("8888888888888888888888888888888");
		
		
	}

	
	
	public static String changeChars(String s){
		String ss = s.replace("coord_", "");
		//System.out.println(ss);
		char[] chars = ss.toCharArray();
		StringBuffer sb =new StringBuffer();
		int count=0;
		for (char c : chars) {
			count++;
			if(c>='A' && c <='Z'){
				c=Character.toLowerCase(c);
				if(count!=1){
					sb.append("_");
				}
			}
			sb.append(c);
		}
		String sss = sb.toString();
		return sss;
	}
	
}
