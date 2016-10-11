package z_bigdata;

import java.util.TreeSet;

public class E {

	
	public static void main(String[] args){
		String shell_task="dim_sales_category,dim_management_category,dim_search_category,dim_cmt_question,dim_cmt_busi_status,m_da_coo_order,m_da_goods_view_rank,m_mem_bl_gp_busi_ow_month,m_kdj_low_purchase_month,m_kdj_low_purchase_shop_month,m_da_cmt_goods_glb,m_da_cmt_goods_hist,m_da_cmt_goods_daily,m_da_cmt_goods_negative,m_sr_eg_new_sku,m_sr_eg_sku_top20,m_mem_bl_gp_busi_ow_month,ls_globle_view_day,ls_globle_visitor_day,ls_globle_visitor_week,m_mp_sale_brand,m_da_coo_view_day,m_da_coo_view_hour,m_kdj_time_lastest2now,m_kdj_time_store_lastest2now,m_da_coo_pay_met,m_uec_flow_all_day_m_uec_levelup_member,ls_kdj_fre_week,ls_kdj_visitor_week,ls_kdj_md_visitor_week,ls_kdj_md_fre_week,m_kdj_online_sale_channel_week,m_kdj_up_down_channel_week,m_kdj_up_down_shop_week,m_kdj_online_sale_shop_week,kdj_muliti_dependence,ls_kdj_md_visitor_day,ls_kdj_uv_day,ls_kdj_visitor_day,m_kdj_low_purchase_shop_day,m_kdj_low_purchase_day,m_kdj_online_sale_channel_day,m_kdj_online_sale_shop_day,m_da_goods_first_up_list,m_kdj_abn_order,dim_da_area,ls_519_mmc_view,ls_519_channel_view,ls_519_site_view,ls_519_core_view,m_da_goods_list_refund_process_day,m_da_good_online_sale_month,m_da_good_online_sale_week,m_sr_eg_api_sec_recode,m_sr_eg_ikey_classid_rate,m_sr_eg_spu_eva_num,m_mem_kip_store_day,m_mem_kip_store_month,m_mem_kip_store_week,m_mem_kip_store_quar,m_da_kdj_pay_type,m_da_kdj_mult_buy_month,m_da_kdj_mult_buy_week,m_da_dzp_sale_order_day,m_da_kdj_promo_coupon,m_uec_flow_trend_week,m_uec_flow_mobile_week,m_da_keywords_flow_rank,m_mp_trans_sum_pay,dim_sys_order,m_da_pcm_prop,m_da_pcm_prop_value,m_mem_old_change_new_day,m_mem_old_change_new_month,m_mem_old_change_new_week";
		String[] shell_split = shell_task.split(",");
		System.out.println("shell length is :"+shell_split.length);
		TreeSet <String> shellSet = new TreeSet<String> ();
		for (String shell : shell_split) {
			shellSet.add(shell);
		}
		String oozie_task="dim_channel_analysis,m_oper_cate_rank_daily,m_sr_eg_goods_num_amount,dim_product_category,m_oper_cate_rank_daily_etl,m_sr_eg_goods_pv_uv,dim_sys_order_etl,m_oper_com_rephase30_daily,m_sr_eg_keywords_pv_uv,dim_sys_product,m_oper_core_order_day,m_uec_all_count_order,dim_sys_product_etl,m_oper_daily_logistics_detail,m_uec_bk_allcount_order,fork_export,m_oper_daily_oper_summary_c,m_uec_bldj_businessindex_order,fork_test,m_oper_daily_oper_summary_c_etl,m_uec_bldj_goods_topsix_order,gad_sale_cate_day,m_oper_daily_oper_summary_d,m_uec_bldj_store_topsix_order,gad_sale_pay_day,m_oper_daily_oper_summary_d_etl,m_uec_channel_day_order,m_com_product_core_member_ct,m_oper_daily_sku,m_uec_channelcount_member,m_com_product_core_sku_count,m_oper_daily_sku_order,m_uec_core_business_order,m_da_abn_daily,m_oper_goods_rank_daily,m_uec_fansgender_sj,m_da_abn_hour,m_oper_goods_rank_daily_etl,m_uec_flow_trend_day,m_da_goods_list,m_oper_hd_logist_day,m_uec_gooddaypay_pcm,m_da_kdj_goods_rank_day,m_oper_hd_order_distr_day,m_uec_goods_qqg_pcm,m_da_kdj_logistics_kpi,m_oper_hd_order_prop_day,m_uec_goods_qqg_sku_pcm,m_da_kdj_missing_order,m_oper_kpi_member_count,m_uec_goods_sku_pcm,m_da_kdj_order_deliver,m_oper_kpi_sale_money,m_uec_goods_ssc_pcm,m_da_logistics_normal_t,m_oper_kpi_sale_money_all,m_uec_goods_sx_pcm,m_da_logistics_normal_t_d,m_oper_kpi_sku_ol,m_uec_goodsbrand_sku_pcm,m_da_mem_reg,m_oper_kpi_sku_ol_all,m_uec_goodscate_sku_pcm,m_da_missing_order,m_oper_lh_logistics_finish,m_uec_jqadd_member,m_da_missing_order_d,m_oper_lh_logistics_freeze,m_uec_jr_all_jr,m_da_order,m_oper_lh_logistics_normal_t,m_uec_jr_paymet_jr,m_da_stock_ol,m_oper_part_order_daily,m_uec_jr_paymetcount_jr,m_da_undeliver_order_d,m_oper_part_order_daily_etl,m_uec_kdjwl_kpi_wl,m_da_unusual_order,m_oper_part_tran_daily,m_uec_levelup_member,m_globle_logistics,m_oper_sum_rephase30_daily,m_uec_member_allinfo_member,m_globle_miss_order,m_oper_summary_member_daily,m_uec_power_data_power,m_globle_order,m_oper_summary_order_daily,m_uec_product_overview_pcm,m_globle_stock_sku,m_oper_summary_uv_daily,m_uec_rrh_ll,m_makt_chan_bounce_day,m_oper_xd_logistics_finish,m_uec_sj_sevenadd_sj,m_makt_chan_order_day,m_oper_xd_logistics_normal_t,m_uec_sxd_allareatop_order,m_makt_chan_uv_day,m_social_renren_fans_reg,m_uec_sxd_businessindex_order,m_mem_con_member_info,m_social_renren_first_order,m_uec_sxd_ordertop_order,m_mem_mkt_dashboard_info,m_social_renren_summary,m_uec_zywl_kpi_wl,m_mem_point_use_info,m_sr_eg_api_sec_recode,m_wx_member_ll,m_mem_reg_member_info,m_sr_eg_goods_eva_num,ph2_channel_analysis_order,m_oper_brand_rank_daily,m_sr_eg_goods_eva_pic_num,ph2_channel_analysis_pv,m_oper_cate_lev1_sku,m_sr_eg_goods_eva_ratio,ph2_channel_analysis_uv,m_oper_cate_order_daily,m_sr_eg_goods_eva_score,ph2_channel_pv_daliy,m_oper_cate_order_daily_etl,m_sr_eg_goods_eva_words,ph2_channel_uv_daliy";
		String[] oozie_split = oozie_task.split(",");
		System.out.println("oozie length is :"+oozie_split.length);
		TreeSet <String>  oozieSet = new TreeSet<String> ();
		for (String oozie : oozie_split) {
			oozieSet.add(oozie);
		}
		
		//在shell中不在oozie中
		System.out.println("在shell中不在oozie中");
		for (String shell : shellSet) {
			if(!oozieSet.contains(shell)){
				System.out.println(shell);
			}
		}
		//在oozie中不在shell中
		System.out.println("在oozie中不在shell中");
		for (String oozie : oozieSet) {
			if(!shellSet.contains(oozie)){
				System.out.println(oozie);
			}
		}
		
		//同时在oozie和shell中
		System.out.println("同时在oozie和shell中");
		for (String oozie : oozieSet) {
			if(shellSet.contains(oozie)){
				System.out.println(oozie);
			}
		}
	}
	
}
