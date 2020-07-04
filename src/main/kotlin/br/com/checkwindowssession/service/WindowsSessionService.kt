package br.com.checkwindowssession.service

import br.com.checkwindowssession.dto.RowDTO
import br.com.checkwindowssession.dto.TableDTO
import org.springframework.stereotype.Service

@Service
class WindowsSessionService {

    companion object {
        private const val SPACE_BETWEEN_PATTERN = "([0-9A-z])\\s([0-9A-z])"
        private const val TWO_MORE_SPACES = "\\s{2,}"
        private const val SINGLE_SPACE = " "
        private const val BREAK_LINE = "\n"
        private const val RDP_TCP_USER = "rdp-tcp"
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
        val formatted = process.removeArrowAndSpace();

        val split = formatted.split(BREAK_LINE)
                .map { it.trim().replace(Regex(TWO_MORE_SPACES), SINGLE_SPACE) }
                .filter { it != "" }

        val titles = split[0].split(SINGLE_SPACE)

        return TableDTO(titles, rowsWithoutheader(split))
    }

    fun rowsWithoutheader(list: List<String>): List<RowDTO> {

        if (list.size <= 1)
            return listOf<RowDTO>()

        return list.subList(1, list.size).filter { it.contains(RDP_TCP_USER) }.map { row -> RowDTO(row.split(SINGLE_SPACE)) }
    }

    fun String.removeArrowAndSpace(): String {
        val removeSpaceBetween = Regex(SPACE_BETWEEN_PATTERN, RegexOption.MULTILINE);
        return this.replace(">", "").replace(removeSpaceBetween, "$1-$2")
    }
}
