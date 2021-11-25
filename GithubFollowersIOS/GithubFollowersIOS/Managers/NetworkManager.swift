//
//  NetworkManagerOrigin.swift
//  SeanAllenTutorial
//
//  Created by Ma Xueyuan on 2020/01/23.
//  Copyright © 2020 Ma Xueyuan. All rights reserved.
//

import UIKit
import shared

class NetworkManager {
    static let shared = NetworkManager()
    private let baseURL = "https://api.github.com/users/"
    let cache = NSCache<NSString, UIImage>()
    
    private init() {
        
    }
    
    func downloadImage(from urlString: String, completed: @escaping (UIImage?) -> Void) {
        if let image = self.cache.object(forKey: urlString as NSString) {
            completed(image)
            return
        }
        
        guard let url = URL(string: urlString) else {
            completed(nil)
            return
        }
        
        let task = URLSession.shared.dataTask(with: url) { [unowned self] data, response, error in
            guard error == nil,
                let response = response as? HTTPURLResponse,
                response.statusCode == 200,
                let data = data,
                let image = UIImage(data: data) else {
                    completed(nil)
                    return
                }
            self.cache.setObject(image, forKey: urlString as NSString)
            completed(image)
        }
        
        task.resume()
    }
}
