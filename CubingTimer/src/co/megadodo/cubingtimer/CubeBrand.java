package co.megadodo.cubingtimer;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public enum CubeBrand {

	VCUBE,
	SHENGSHOU,
	YJ,
	DIANSHENG,
	MF8,
	MOFANGGE,
	DFANTIXCYCLONEBOYS,
	DFANTIXQIYI,
	DFANTIXCARBON,
	DFANTIXXMANVOLT,
	DFANTIXYONGJUNYILENG,
	DFANTIX,
	MEFFERTS,
	MOYU,
	MOYU_AOFU,
	CALVINS,
	RUBIKS,
	WITEDEN,
	LANLAN,
	DAVIDS,
	CUBERSPEED,
	AYI,
	OTHER;
	
	public static CubeBrand promptForBrand(Cube c) {
		
		String[] list = new String[CubeBrand.values().length];
		CubeBrand[] brands = CubeBrand.values();
		for(int i = 0; i < list.length; i++) {
			list[i] = brands[i].toString();
		}
		JComboBox<String> jcb = new JComboBox<String>(list);
		jcb.setSelectedIndex(OTHER.ordinal());
//		if(c == Cube.CUBE_2x2 || c == Cube.CUBE_3x3 || c == Cube.CUBE_4x4 || c == Cube.CUBE_5x5 || c == Cube.CUBE_6x6) {
//			jcb.setSelectedIndex(DFANTIXCYCLONEBOYS.ordinal());
//		}
		jcb.setSelectedIndex(c.brand.ordinal());
		JOptionPane.showMessageDialog(null, jcb, "Select Brand", JOptionPane.PLAIN_MESSAGE);
		return parseCubeBrand(((String)jcb.getSelectedItem()));
		
	}
	
	public String toString() {
		switch(this) {
			case CALVINS: return "Calvin's";
			case CUBERSPEED: return "Cuberspeed";
			case DAVIDS: return "David's";
			case DFANTIX: return "D-Fantix";
			case DFANTIXCARBON: return "D-Fantix Carbon";
			case DFANTIXCYCLONEBOYS: return "D-Fantix Cyclone Boys";
			case DFANTIXQIYI: return "D-Fantix Qiyi";
			case DFANTIXXMANVOLT: return "D-Fantix X-Man Volt";
			case DFANTIXYONGJUNYILENG: return "D-Fantix Yongjun Yileng";
			case DIANSHENG: return "Diansheng";
			case LANLAN: return "LanLan";
			case MEFFERTS: return "Meffert's";
			case MF8: return "Mf8";
			case MOYU: return "Moyu";
			case MOYU_AOFU: return "Moyu Aofu";
			case OTHER: return "Other";
			case RUBIKS: return "Rubik's";
			case SHENGSHOU: return "Shengshou";
			case VCUBE: return "V-Cube";
			case WITEDEN: return "WitEden";
			case YJ: return "YJ";
			case AYI: return "Ayi";
			case MOFANGGE: return "MO FANG GE";
		}
		return "Null";
	}
	
	public static CubeBrand parseCubeBrand(String str) {
		if(str.equals("MO FANG GE")) return MOFANGGE;
		if(str.equals("Calvin's")) return CALVINS;
		if(str.equals("CUberspeed")) return CUBERSPEED;
		if(str.equals("David's")) return DAVIDS;
		if(str.equals("D-Fantix")) return DFANTIX;
		if(str.equals("D-Fantix Carbon")) return DFANTIXCARBON;
		if(str.equals("D-Fantix Cyclone Boys")) return DFANTIXCYCLONEBOYS;
		if(str.equals("D-Fantix Qiyi")) return DFANTIXQIYI;
		if(str.equals("D-Fantix X-Man Volt")) return DFANTIXXMANVOLT;
		if(str.equals("D-Fantix Yongjun Yileng")) return DFANTIXYONGJUNYILENG;
		if(str.equals("Diansheng")) return DIANSHENG;
		if(str.equals("LanLan")) return LANLAN;
		if(str.equals("Meffert's")) return MEFFERTS;
		if(str.equals("Mf8")) return MF8;
		if(str.equals("Moyu")) return MOYU;
		if(str.equals("Other")) return OTHER;
		if(str.equals("Rubik's")) return RUBIKS;
		if(str.equals("Shengshou")) return SHENGSHOU;
		if(str.equals("V-Cube")) return VCUBE;
		if(str.equals("WitEden")) return WITEDEN;
		if(str.equals("YJ")) return YJ;
		if(str.equals("Ayi")) return AYI;
		if(str.equals("Moyu Aofu")) return MOYU_AOFU;
		return null;
	}
	
}
