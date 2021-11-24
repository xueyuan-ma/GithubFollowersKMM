//
//  PersistenceManager.swift
//  SeanAllenTutorial
//
//  Created by Ma Xueyuan on 2020/05/23.
//  Copyright © 2020 Ma Xueyuan. All rights reserved.
//

import Foundation
import shared

enum PersistenceActionType {
    case add, remove
}

enum PersistenceManager {
    static private let defaults = UserDefaults.standard
    
    enum Keys {
        static let favorites = "favorites"
    }
    
    static func updateWith(favorite: Follower, actionType: PersistenceActionType, completed: @escaping (GFError?) -> Void) {
        retrieveFavorites { (result) in
            switch result {
            case .success(var favorites):
                switch actionType {
                case .add:
                    guard !favorites.contains(favorite) else {
                        completed(.alreadyInFavorites)
                        return
                    }
                    
                    favorites.append(favorite)
                case .remove:
                    favorites.removeAll { (f) -> Bool in
                        f.login == favorite.login
                    }
                }
                
                completed(save(favorites: favorites))
            case .failure(let error):
                completed(error)
            }
        }
    }
    
    static func retrieveFavorites(completed: @escaping (Result<[Follower], GFError>) -> Void) {
        guard let favoritesData = defaults.object(forKey: Keys.favorites) as? Data else {
            completed(.success([]))
            return
        }
        
        do {
            let decoder = JSONDecoder()
            //let favorites = try decoder.decode([Follower].self, from: favoritesData)
			let favorites = [Follower]()
            completed(.success(favorites))
        } catch {
            completed(.failure(.unableToFavorite))
        }
    }
    
    static func save(favorites: [Follower]) -> GFError? {
        do {
            let encoder = JSONEncoder()
            //let encodedFavorites = try encoder.encode(favorites)
			let encodedFavorites = try encoder.encode("Fuck")
            defaults.set(encodedFavorites, forKey: Keys.favorites)
        } catch {
            return .unableToFavorite
        }
        
        return nil
    }
}
