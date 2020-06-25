package br.com.checkwindowssession.service

import br.com.checkwindowssession.dto.RowDTO
import br.com.checkwindowssession.dto.TableDTO
import org.springframework.stereotype.Service

@Service
class WindowsSessionService {

    companion object {
        private const val SPACE_BETWEEN_PATTERN = "([0-9A-z])\\s([0-9A-z])"
        private const val TWO_MORE_SPACES = "\\s{2,}"
        private const val BREAK_LINE = "\n"
    }

    fun executeWindowsCmd(): String {
        val process = Runtime.getRuntime().exec("quser")
        return process.inputStream.bufferedReader().readText()
    }

    fun getUsersInSession(): TableDTO {
        val processStr = executeWindowsCmd()
        return transform(processStr)
    }

    fun transform(process: String): TableDTO {

        val removeSpaceBetween = Regex(SPACE_BETWEEN_PATTERN, RegexOption.MULTILINE);
        val formatted = process.replace(">", "").replace(removeSpaceBetween, "$1-$2")

        val split = formatted.split(BREAK_LINE)
                .map { it.trim().replace(Regex(TWO_MORE_SPACES), " ") }
                .filter { it != "" }

        val titles = split[0].split(" ")

        var rows = listOf<RowDTO>()
        if (split.size > 1) {
            rows = split.subList(1, split.size).filter{ it.contains("rdp-tcp") }.map { row -> RowDTO(row.split(" ")) }
        }
        return TableDTO(titles, rows)
    }
}