export class Therapy {
    id: number;
    name: string;
    description: string;
    type: string; // Assuming there is a type field which might be Enum or String
  
    constructor(
      id: number,
      name: string,
      description: string,
      type: string
    ) {
      this.id = id;
      this.name = name;
      this.description = description;
      this.type = type;
    }
  }
  