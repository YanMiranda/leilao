function deleteNullObject(obj) {
  Object.entries(obj).forEach(([key, value]) => {
    if (value !== null && typeof (value) === 'object') {
      const result = Object.values(value).every((item) => ((item === '' || item === null)));
      if (result) {
        delete obj[key];
      } else {
        const exist = Object.values(value).some((item) => (item !== null && typeof (item) === 'object'));
        if (exist) {
          deleteNullObject(value);
        }
      }
    }
  });
  return obj;
}

function deleteEmptyValues(obj) {
  Object.entries(obj).forEach(([key, value]) => {
    if (value === null) delete obj[key];

    if (value === '') {
      const result = Object.values(value).every((item) => ((item === '' || item === null)));
      if (result) {
        delete obj[key];
      } else {
        const exist = Object.values(value).some((item) => (item !== null && typeof (item) === 'object'));
        if (exist) {
          deleteNullObject(value);
        }
      }
    }
  });
  const newObject = JSON.parse(JSON.stringify(obj));
  return newObject;
}

export default deleteEmptyValues;
