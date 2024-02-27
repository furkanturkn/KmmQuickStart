//
//  Font.swift
//  iosApp
//
//  Created by Furkan Turkan on 21.02.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

enum CustomFont: String {
    case regular = "Roboto-Regular"
    case medium = "Roboto-Medium"
    case bold = "Roboto-Bold"
}

extension Font {
    static func custom(_ font: CustomFont, size: CGFloat) -> SwiftUI.Font {
        SwiftUI.Font.custom(font.rawValue, size: size)
    }
}

struct Typography {
    static let displayLarge = Font.custom(.bold, size: 30)
    static let displayMedium = Font.custom(.bold, size: 24)
    static let displaySmall = Font.custom(.medium, size: 18)
    static let headlineLarge = Font.custom(.regular, size: 14)
    static let headlineMedium = Font.custom(.regular, size: 12)
}
