//
//  FavoritesListVC.swift
//  SeanAllenTutorial
//
//  Created by Ma Xueyuan on 2020/01/08.
//  Copyright © 2020 Ma Xueyuan. All rights reserved.
//

import UIKit
import shared

class FavoritesListVC: GFDataLoadingVC {
    let tableView = UITableView()
    var favorites: [Follower] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configureViewController()
        configureTableView()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        getFavorites()
    }
    
    func configureViewController() {
        view.backgroundColor = .systemBackground
        title = "Favorites"
        navigationController?.navigationBar.prefersLargeTitles = true
    }
    
    func configureTableView() {
        view.addSubview(tableView)
        tableView.frame = view.bounds
        tableView.rowHeight = 80
        tableView.delegate = self
        tableView.dataSource = self
        tableView.removeExcessCells()
        tableView.register(FavoriteCell.self, forCellReuseIdentifier: FavoriteCell.reuseID)
    }
    
    func getFavorites() {
		let favorites = Manager.persistenceManager.retrieveFavorites()
		if favorites.isEmpty {
			showEmptyStateView(with: "No Favorites?\nAdd one on the follower screen.", in: self.view)
		} else {
			self.favorites = favorites
			tableView.reloadData()
			view.bringSubviewToFront(self.tableView)
		}
    }
}

extension FavoritesListVC: UITableViewDataSource, UITableViewDelegate {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return favorites.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: FavoriteCell.reuseID) as! FavoriteCell
        let favorite = favorites[indexPath.row]
        cell.set(favorite: favorite)
        return cell
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let favorite = favorites[indexPath.row]
        let destVC = FollowerListVC(username: favorite.login)
        
        navigationController?.pushViewController(destVC, animated: true)
    }
    
    func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        guard editingStyle == .delete else { return }

		Manager.persistenceManager.deleteFavorite(follower: favorites[indexPath.row])
		favorites.remove(at: indexPath.row)
		tableView.deleteRows(at: [indexPath], with: .left)
    }
}
