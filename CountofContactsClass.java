/* CountofContactsClass.apxc */

public class CountofContactsClass 
{
	public static void contactCount(List<Contact> conList, Map<Id,Contact> oldMap)
	{
        Map<Id,Integer> countMap = new Map<Id,Integer>();
		set<Id> accIds = new set<Id>();
		if(!conList.isEmpty())
		{
			/** Getting AccountId's to update count of contacts **/ 
			for(Contact newContact : conList)
			{
				/** for update operation, when Contact owner changes **/
				if(!oldMap.isEmpty())
				{
					Contact oldContact = oldMap.get(newContact.Id);
					if(newContact.AccountId != oldContact.AccountId)
					{
						accIds.add(newContact.AccountId);
						accIds.add(oldContact.AccountId);
					}
				}
				
				/** for Insert, Delete, Undelete of a contact **/
				if(newContact.AccountId != Null)
				{
					accIds.add(newContact.AccountId);
				}
			}
        }
			/* query the accounts and related contacts count */
			for(AggregateResult res : [SELECT AccountId, COUNT(Id) conCount FROM Contact 
										WHERE AccountId IN:accIds Group By AccountId])
			{
				Id accId = (Id) res.get('AccountId');
				Integer conCount = (Integer) res.get('conCount');
				countMap.put(accId,conCount);
			}
            /* query the accounts by contact accountids */
			List<Account> accList = [SELECT Id, Name, Number_of_Contacts__c FROM Account WHERE Id IN:accIds];
			List<Account> listToUpdate = new List<Account>();
			for(Account ac : accList)
			{
				if(countMap.containsKey(ac.Id))
				{
					integer conCount = countMap.get(ac.Id);
					ac.Number_of_Contacts__c = conCount;
					listToUpdate.add(ac);
				}
			}
			if(!listToUpdate.isEmpty())
			{
				update listToUpdate;
			}
	}
}