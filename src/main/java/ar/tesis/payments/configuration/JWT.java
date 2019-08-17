package ar.tesis.payments.configuration;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.json.JSONException;
import org.json.JSONObject;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;    


public class JWT {
	
	//Sample method to construct a JWT
	public String createJWT(String identity, String role, long ttlMillis) {

	    //The JWT signature algorithm we will be using to sign the token
	    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

	    long nowMillis = System.currentTimeMillis();
	    //Date now = new Date(nowMillis);

	    //We will sign our JWT with our ApiKey secret
	    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("1234");
	    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
	    


	    //Let's set the JWT Claims
	    /*JwtBuilder builder = Jwts.builder().setId(id)
	                                .setIssuedAt(now)
	                                .setSubject(subject)
	                                .setIssuer(issuer)
	                                .signWith(signatureAlgorithm, signingKey);
	    */
	    
	    JSONObject root = new JSONObject();
	    try {
			root.put("identity", identity);
			root.put("role", role);
		    if (ttlMillis >= 0) {
			    long expMillis = nowMillis + ttlMillis;
			        //Date exp = new Date(expMillis);
			        root.put("expiration",expMillis);
			    }
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    


	    /*
	    String jwt = Jwts.builder()
	            .setPayload(root.toJSONString())
	            .signWith(SignatureAlgorithm.ES384, configuration.getServerPrivateKey())
	            .compact(); // Sign and build the JWT
	    */
	    
	    //TODO: root.toJSONString()
	    JwtBuilder builder = Jwts.builder().setPayload(root.toString()).signWith(signatureAlgorithm, signingKey);

	    //if it has been specified, let's add the expiration

	    //Builds the JWT and serializes it to a compact, URL-safe string
	    return builder.compact();
	}
	
	//Sample method to validate and read the JWT
	public void parseJWT(String jwt) {
	 
	    //This line will throw an exception if it is not a signed JWS (as expected)
	    Claims claims = Jwts.parser()         
	       .setSigningKey(DatatypeConverter.parseBase64Binary("1234"))
	       .parseClaimsJws(jwt).getBody();
	    System.out.println("Token Decripted");
	    System.out.println("claims: " + claims);
	    System.out.println("Identity: " + claims.get("identity"));
	    System.out.println("Role: " + claims.get("role"));
	    System.out.println("Expiration: " +claims.get("expiration"));
	}
	
}


