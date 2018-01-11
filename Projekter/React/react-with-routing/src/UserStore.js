
//place this in a file userStore.js
class UserStore {

//this._users: privat array, den bliver ikke vist direkt, men via getter og setter
  constructor() {
    this._users = [
          { 
            "gender": "male", 
            "title": "mr", 
            "first": "francisco", 
            "last": "medina", 
            "street": "2748 w dallas st", 
            "city": "flowermound", 
            "state": "new jersey", 
            "zip": "77511", 
            "email": "francisco.medina65@example.com",
            "dob": "454252284", 
            "phone": "(757)-889-2571", 
            "cell": "(113)-542-2123", 
            "picture": { 
              "large": "http://api.randomuser.me/portraits/men/22.jpg", 
              "thumbnail": "http://api.randomuser.me/portraits/thumb/men/22.jpg" 
                      }
          },
          { 
            "gender": "female", 
            "title": "mrs", 
            "first": "sherry", 
            "last": "elliott", 
            "street": "3251 brown terrace", 
            "city": "wichita falls", 
            "state": "washington", 
            "zip": "79455", 
            "email": "sherry.elliott17@example.com", 
            "dob": "224238139", 
            "phone": "(225)-793-2067", 
            "cell": "(968)-555-1402", 
            "picture": { 
              "large": "http://api.randomuser.me/portraits/women/37.jpg", 
              "thumbnail": "http://api.randomuser.me/portraits/thumb/women/37.jpg" 
                        } 
            },
            { 
              "gender": "male", 
              "title": "mr", 
              "first": "johnny", 
              "last": "medina", 
              "street": "1313 samaritan dr", 
              "city": "redding", 
              "state": "new hampshire", 
              "zip": "43269", 
              "email": "johnny.medina76@example.com", 
              "dob": "259176886", 
              "phone": "(991)-957-7139", 
              "cell": "(502)-773-1487", 
              "picture": { 
                "large": "http://api.randomuser.me/portraits/men/90.jpg", 
                "thumbnail": "http://api.randomuser.me/portraits/thumb/men/90.jpg" 
                        } 
              }  
        ]
  }

  get users(){
    return this._users;
  }
}

let userStore = new UserStore();
export default userStore;
