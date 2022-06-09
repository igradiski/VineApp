const convertDateForTable = (date: any) => {
  var oldDateFormat = new Date(date);
  return (
    oldDateFormat.toLocaleDateString() +
    " " +
    oldDateFormat.toLocaleTimeString()
  );
};

export default convertDateForTable;
