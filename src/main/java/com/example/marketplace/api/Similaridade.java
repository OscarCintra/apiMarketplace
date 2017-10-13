package com.example.marketplace.api;

public class Similaridade {

    public float checkSimilarity(String sString1, String sString2) {
    	
    	try {

    	sString1=sString1.toUpperCase();
    	sString2=sString2.toUpperCase();
    	
        if(sString1.length() != sString2.length()) {
            int iDiff = Math.abs(sString1.length() - sString2.length());
            int iLen = Math.max(sString1.length(), sString2.length());
            String sBigger, sSmaller, sAux;

            if(iLen == sString1.length()) {
                sBigger = sString1;
                sSmaller = sString2;
            }
            else {
                sBigger = sString2;
                sSmaller = sString1;
            }

            float fSim, fMaxSimilarity = Float.MIN_VALUE;
            for(int i = 0; i <= sSmaller.length(); i++) {
                sAux = sSmaller.substring(0, i) + sBigger.substring(i, i+iDiff) + sSmaller.substring(i);
                fSim = checkSimilaritySameSize(sBigger,  sAux);
                if(fSim > fMaxSimilarity)
                    fMaxSimilarity = fSim;
            }
            return fMaxSimilarity - (1f * iDiff) / iLen;

        } else
            return checkSimilaritySameSize(sString1, sString2);
		
	}
    	catch (Exception e) {
    		
		return 0;
	}
}

    private  float checkSimilaritySameSize(String sString1, String sString2) {
    	
    	try {
			
		
        if(sString1.length() != sString2.length())
            throw new Exception("Strings devem ter o mesmo tamanho!");

        int iLen = sString1.length();
        int iDiffs = 0;

        for(int i = 0; i < iLen; i++)
            if(sString1.charAt(i) != sString2.charAt(i))
                iDiffs++;

        return 1f - (float) iDiffs / iLen;
        
    	} 
    	
    	catch (Exception e) {
    		return 0;
		}
    }
    
    
    
}
	
