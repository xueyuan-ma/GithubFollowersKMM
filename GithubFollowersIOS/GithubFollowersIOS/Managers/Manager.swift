//
//  Manager.swift
//  GithubFollowersIOS
//
//  Created by Ma, Xueyuan | MTSD on 2021/11/24.
//  Copyright © 2021 Ma Xueyuan. All rights reserved.
//

import Foundation
import shared

final class Manager {
	static let networkManager = KTNetworkManager()
	static let persistenceManager = KTPersistenceManager(databaseDriverFactory: DatabaseDriverFactory())

	private init() { }
}
