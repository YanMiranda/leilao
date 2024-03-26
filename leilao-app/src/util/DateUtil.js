import moment from "moment";

const formatdate = function v(cellValue) {
  if (cellValue === null || cellValue === undefined || cellValue === '') {
    return cellValue;
  }
  moment.locale('pt-br');
  return moment(cellValue).format('DD/MM/YYYY');
};

const isdate = function v(cellValue) {
  if (cellValue === null || cellValue === undefined || cellValue === '') {
    return cellValue;
  }
  return moment(cellValue, moment.ISO_8601, true).isValid();
};

const formatdatetime = function v(cellValue) {
  if (cellValue === null || cellValue === undefined || cellValue === '') {
    return cellValue;
  }
  moment.locale('pt-br');
  return moment(cellValue).format('DD/MM/YYYY [às] HH:mm:ss');
};

const formatDateToRequest = function v(cellValue) {
  if (cellValue === null || cellValue === undefined || cellValue === '') {
    return cellValue;
  }
  return moment(cellValue).format();
};

export { formatdate, isdate, formatdatetime, formatDateToRequest };