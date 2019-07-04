package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import ecapi.model.KebiaoModel;
/**
 * 
 * @author wjd
 *
 */
public class DBUtil1 {
	
	public static List<KebiaoModel> getKB(Map<String, String> controlMap) {

		List<KebiaoModel> kebiaoList = new ArrayList<KebiaoModel>();
		List<KebiaoModel> kebiaoListResult = new ArrayList<KebiaoModel>();
	

				
				KebiaoModel kebiaoModel1 = new KebiaoModel();

				kebiaoModel1.setJsh("15");
				kebiaoModel1.setJsid("NMD00305");
				KebiaoModel kebiaoModel2 = new KebiaoModel();
				kebiaoModel2.setJsh("62");
				kebiaoModel2.setJsid("NMD00306");
			/*	KebiaoModel kebiaoModel3 = new KebiaoModel();
				kebiaoModel3.setJsh("16");
				kebiaoModel3.setJsid("9");*/
				KebiaoModel kebiaoModel4 = new KebiaoModel();
				kebiaoModel4.setJsh("88");
				kebiaoModel4.setJsid("NMC00305");
				KebiaoModel kebiaoModel5 = new KebiaoModel();
				kebiaoModel5.setJsh("99");
				kebiaoModel5.setJsid("NMB00305");
				
				KebiaoModel kebiaoModel6 = new KebiaoModel();
				kebiaoModel6.setJsh("11");
				kebiaoModel6.setJsid("NMA00305");
				KebiaoModel kebiaoModel7 = new KebiaoModel();
				kebiaoModel7.setJsh("22");
				kebiaoModel7.setJsid("NMD00105");
				KebiaoModel kebiaoModel8 = new KebiaoModel();
				kebiaoModel8.setJsh("26");
				kebiaoModel8.setJsid("NMB00108");
			/*	KebiaoModel kebiaoModel6 = new KebiaoModel();
				kebiaoModel6.setJsh("99");
				kebiaoModel6.setJsid("58");
				KebiaoModel kebiaoModel7 = new KebiaoModel();
				kebiaoModel7.setJsh("51");
				kebiaoModel7.setJsid("48");
				KebiaoModel kebiaoModel8 = new KebiaoModel();
				kebiaoModel8.setJsh("55");
				kebiaoModel8.setJsid("3");
				KebiaoModel kebiaoModel9 = new KebiaoModel();
				kebiaoModel9.setJsh("555");
				kebiaoModel9.setJsid("30");
				KebiaoModel kebiaoModel10 = new KebiaoModel();
				kebiaoModel10.setJsh("1");
				kebiaoModel10.setJsid("31");*/
			/*	KebiaoModel kebiaoModel11 = new KebiaoModel();
				
				kebiaoModel11.setJsh("87");
				kebiaoModel11.setJsid("45");*/
				/*kebiaoModel1.setKcsjmx(",101,102");
				kebiaoList.add(kebiaoModel1);
				kebiaoModel2.setKcsjmx(",101,102");
				kebiaoList.add(kebiaoModel2);*/
				/*kebiaoModel3.setKcsjmx(",101,102");
				kebiaoList.add(kebiaoModel3);*/
				kebiaoModel1.setKcsjmx(",101,102");
				kebiaoList.add(kebiaoModel1);
				kebiaoModel2.setKcsjmx(",101,102");
				kebiaoList.add(kebiaoModel2);
				kebiaoModel4.setKcsjmx(",101,102");
				kebiaoList.add(kebiaoModel4);
				kebiaoModel5.setKcsjmx(",101,102");
				kebiaoList.add(kebiaoModel5);
				kebiaoModel6.setKcsjmx(",101,102");
				kebiaoList.add(kebiaoModel6);
				kebiaoModel7.setKcsjmx(",101,102");
				kebiaoList.add(kebiaoModel7);
				kebiaoModel8.setKcsjmx(",101,102");
				kebiaoList.add(kebiaoModel8);
				/*kebiaoModel11.setKcsjmx(",101,102");
				kebiaoList.add(kebiaoModel11);
				*/
				/*KebiaoModel kebiaoModel2 = new KebiaoModel();
				 KebiaoModel kebiaoModel2 = new KebiaoModel();
				 KebiaoList.add(kebiaoMode12)
				kebiaoModel2.setJsh("88");
				kebiaoModel2.setJsid("7");
				kebiaoModel2.setKcsjmx(",203,204");
				kebiaoList.add(kebiaoModel2);*/
				//kebiaoList.add(kebiaoMode10);
				/*KebiaoModel kebiaoModel11 = new KebiaoModel();
                kebiaoModel11.setJst("002号教室");
				kebiaoModel11.setJsh("002号教室");
				kebiaoModel11.setJsid("MDB302");
				kebiaoModel11.setKcsjmx(",101,102");
				kebiaoList.add(kebiaoModel11);
				kebiaoList.add(kebiaoModel11);
				KebiaoModel kebiaoModel22 = new KebiaoModel();
				KebiaoModel kebiaoMode122 = new KebiaoModel();
				kebiaoModel kebiaoMode122 = new KebiaoModel();
				kebiaoModel22.setJsh("002号教室");
				kebiaoModel22.setJsid("MDB302");
				kebiaoModel22.setKcsjmx(",203,204");
				kebiaoList.add(kebiaoModel22);
				*/
				
			/*	KebiaoModel kebiaoModel3 = new KebiaoModel();
				
				kebiaoModel3.setJsh("003号教室");
				kebiaoModel3.setJsid("003");
				kebiaoModel3.setKcsjmx(",111,112");
				kebiaoMode13.setKcsjmx(",111,112");
				kebiaoMode13.setKcsjmx(",111,112");
				kebiaoList.add(kebiaoModel3);
		*/
		for(KebiaoModel kebiao:kebiaoList){
    		String kcsjmx = Pattern.compile(",[\\d]").matcher(kebiao.getKcsjmx()).replaceAll("").replaceAll(",", "");
    		kebiao.setKcsjmx(kcsjmx);
    		//if(controlMap.containsKey(kcsjmx)){
    			kebiaoListResult.add(kebiao);
    		//}
		}
		return kebiaoListResult;
	}
}
