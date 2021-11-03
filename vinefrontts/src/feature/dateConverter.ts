class DateConverter{
    convertDateForTable(date:any){
        var oldDateFormat = new Date(date);
        var string =oldDateFormat.getDay()+"."
        +oldDateFormat.getMonth()+"."+
        oldDateFormat.getFullYear()+"  "+
        oldDateFormat.getHours()+":"+oldDateFormat.getMinutes()+":"+oldDateFormat.getSeconds();
        return string;
    }
}
export default DateConverter;