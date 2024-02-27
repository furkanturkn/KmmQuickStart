//
//  Image.swift
//  iosApp
//
//  Created by Furkan Turkan on 21.02.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Shared
import SwiftUI

extension Image {
    init(resource: KeyPath<SharedRes.images, Shared.ImageResource>) {
        self.init(uiImage: SharedRes.images()[keyPath: resource].toUIImage()!)
    }
}
