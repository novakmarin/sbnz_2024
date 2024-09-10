import { User } from "./user";

export class Doctor extends User {
    constructor(
        id?: number,
        username?: string,
        password?: string,
        firstName?: string,
        lastName?: string
    ) {
        super(id, username, password, firstName, lastName);
    }
}
