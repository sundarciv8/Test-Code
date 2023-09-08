public with sharing class displayRecentAccountClass 
{
    @AuraEnabled(Cacheable=true)
    public static List<Account> getAccounts()
    {
        return [SELECT Id, Name, Industry, CreatedDate FROM Account Order By CreatedDate DESC LIMIT 10 ];
    }
}