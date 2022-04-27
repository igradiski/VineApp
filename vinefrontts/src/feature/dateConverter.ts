class DateConverter {
  convertDateForTable(date: any) {
    var oldDateFormat = new Date(date);
    return (
      oldDateFormat.toLocaleDateString() +
      " " +
      oldDateFormat.toLocaleTimeString()
    );
  }

  convertDateForSpricanje(date: any) {
    var oldDateFormat = new Date(date);
    return (
      oldDateFormat.getUTCDate() +
      "." +
      oldDateFormat.getUTCMonth() +
      "." +
      oldDateFormat.getUTCFullYear()
    );
  }
}
export default DateConverter;
