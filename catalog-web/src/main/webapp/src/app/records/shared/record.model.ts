export class Record {
  id: number;
  price: number;
  albumName: string;
  inStock: number;
  typeOfRecord: RecordType;
}


export enum RecordType {
  CD='CD', VINYL='VINYL', TAPE='TAPE'
}
