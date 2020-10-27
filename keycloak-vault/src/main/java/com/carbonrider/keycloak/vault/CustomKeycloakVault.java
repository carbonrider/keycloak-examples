package com.carbonrider.keycloak.vault;

import org.jboss.security.vault.SecurityVault;
import org.jboss.security.vault.SecurityVaultException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Properties;

public class CustomKeycloakVault implements SecurityVault {
    protected boolean finishedInit = false;

    private Properties props;

    /*
     * @see org.jboss.security.vault.SecurityVault#init(java.util.Map)
     */
    public void init(Map<String, Object> options) throws SecurityVaultException {
        props = new Properties();

        props.setProperty("DB_PASSWORD", System.getenv("ENV_DB_PASSWORD"));

        finishedInit = true;
    }

    /*
     * @see org.jboss.security.vault.SecurityVault#isInitialized()
     */
    public boolean isInitialized() {
        return finishedInit;
    }

    /*
     * @see org.jboss.security.vault.SecurityVault#handshake(java.util.Map)
     */
    public byte[] handshake(Map<String, Object> handshakeOptions) throws SecurityVaultException {
        return new byte[0];
    }

    /*
     * @see org.jboss.security.vault.SecurityVault#keyList()
     */
    public Set<String> keyList() throws SecurityVaultException {
        return new HashSet<String>(Arrays.asList("DB_PASSWORD"));
    }

    /*
     * @see org.jboss.security.vault.SecurityVault#store(java.lang.String,
     * java.lang.String, char[], byte[])
     */
    public void store(String vaultBlock, String attributeName, char[] attributeValue, byte[] sharedKey)
            throws SecurityVaultException {
        throw new SecurityVaultException("Storing values not implemented.");
    }

    /*
     * @see org.jboss.security.vault.SecurityVault#retrieve(java.lang.String,
     * java.lang.String, byte[])
     */
    public char[] retrieve(String vaultBlock, String attributeName, byte[] sharedKey) throws SecurityVaultException {
        String value = this.props.getProperty(attributeName);
        return value.toCharArray();
    }

    /**
     * @see SecurityVault#exists(String, String)
     */
    public boolean exists(String vaultBlock, String attributeName) throws SecurityVaultException {
        return true;
    }

    /*
     * @see org.jboss.security.vault.SecurityVault#remove(java.lang.String,
     * java.lang.String, byte[])
     */
    public boolean remove(String vaultBlock, String attributeName, byte[] sharedKey) throws SecurityVaultException {
        throw new SecurityVaultException("Removing secrets not implemented.");
    }

}
