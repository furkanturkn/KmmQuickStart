//
//  Router.swift
//  iosApp
//
//  Created by Furkan Turkan on 23.02.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

final class Router: ObservableObject {

    public enum Destination: Codable, Hashable {
        case login
        case home

        var stringValue: String {
            switch self {
            case .login:
                return "login"
            case .home:
                return "home"
            }
        }
    }

    @Published var navPath = NavigationPath()

    func navigate(to destination: Destination) {
        navPath.append(destination)
    }

    func navigateBack() {
        navPath.removeLast()
    }


    func popScreens(_ amount: Int) {
        navPath.removeLast(amount)
    }

    func navigateToRoot() {
        navPath.removeLast(navPath.count)
    }
}
