import { LightningElement, wire } from 'lwc';
import GetAccounts from '@salesforce/apex/displayRecentAccountClass.getAccounts'
const COLUMNS = [
    { label: 'Id', fieldName: 'Id' },
    { label: 'Name', fieldName: 'Name' },
    { label: 'Industry', fieldName: 'Industry', type: 'Picklist' },
    { label: 'CreatedDate', fieldName: 'CreatedDate' }
];
export default class DisplayRecentAccounts extends LightningElement {
    columns = COLUMNS
    data=[]
    @wire(GetAccounts)
    accountList({data,error})
    {
        this.data = data
    }
}