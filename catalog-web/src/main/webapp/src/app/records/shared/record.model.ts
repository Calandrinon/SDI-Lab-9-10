export class Record {
  id: number;
  price: number;
  albumName: string;
  inStock: number;
  typeOfRecord: RecordType;

  constructor(id, price, albumName, inStock, typeOfRecord) {
    this.id = id;
    this.price = price;
    this.albumName = albumName;
    this.inStock = inStock;
    this.typeOfRecord = typeOfRecord;
  }
}


export enum RecordType {
  CD='CD', VINYL='VINYL', TAPE='TAPE'
}
