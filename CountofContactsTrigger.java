/* CountOfContacts.apxt */
trigger CountOfContacts on Contact (after insert, after update, after delete, after undelete)
{
    if(Trigger.isAfter)
    {
        if(Trigger.isInsert || Trigger.isUndelete)
        {
            CountofContactsClass.contactCount(Trigger.New, null);
        }
        if(Trigger.isUpdate)
        {
            CountofContactsClass.contactCount(Trigger.New, Trigger.OldMap);
        }
        if(Trigger.isDelete)
        {
            CountofContactsClass.contactCount(Trigger.old, null);
        }
    }
}