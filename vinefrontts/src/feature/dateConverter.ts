class DateConverter{
    convertDateForTable(date:any){
        var oldDateFormat = new Date(date);
        var string =oldDateFormat.getDay()+"."
        +oldDateFormat.getMonth()+"."+
        oldDateFormat.getFullYear()+"  "+
        oldDateFormat.getHours()+":"+oldDateFormat.getMinutes()+":"+oldDateFormat.getSeconds();
        return string;
    }

    convertDateForSpricanje(date:any){
        var oldDateFormat = new Date(date);
        return oldDateFormat.getUTCDate()+"."+oldDateFormat.getUTCMonth()+"."+oldDateFormat.getUTCFullYear()
    }
}
export default DateConverter;