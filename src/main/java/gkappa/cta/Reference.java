package gkappa.cta;

public class Reference {
	
    // Mod info
	public static final String MOD_NAME = "@MODNAME@";
    public static final String MOD_ID = "cta";
    public static final String MOD_VERSION = "@VERSION@";
    
    // MOD ID's
    public static final String MOD_FORGE = "forge";
    public static final String MOD_FORGE_VERSION_MIN = "14.23.5.2855";
    
    // Dependencies
    public static final String MOD_DEPENDENCIES =
            "required-after:" + MOD_FORGE + "@[" + MOD_FORGE_VERSION_MIN + ",);";
}
